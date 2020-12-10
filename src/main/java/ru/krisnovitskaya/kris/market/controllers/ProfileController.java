package ru.krisnovitskaya.kris.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.dto.JwtRequest;
import ru.krisnovitskaya.kris.market.dto.ProfileDto;
import ru.krisnovitskaya.kris.market.dto.TestDto;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.exceptions.MarketError;
import ru.krisnovitskaya.kris.market.exceptions.WrongPasswordException;
import ru.krisnovitskaya.kris.market.services.ProfileService;
import ru.krisnovitskaya.kris.market.services.UserService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public ProfileDto showProfile(Principal principal){
        Profile p = profileService.findProfileByUsername(principal.getName());
        return new ProfileDto(p);
    }

    @PostMapping
    public ProfileDto changeProfile(@RequestParam Map<String, String> params, Principal principal){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principal.getName(), params.get("password")));
        } catch (BadCredentialsException ex) {
            throw new WrongPasswordException("Wrong password");
        }
        Profile profile = userService.findByUsername(principal.getName()).orElseThrow().getProfile();
        profile.setFirstname(params.get("firstname"));
        profile.setLastname(params.get("lastname"));
        profile.setPhone(Integer.parseInt(params.get("phone")));
        profile.setBirthYear(Integer.parseInt(params.get("birthYear")));
        profile.setSex(params.get("sex"));
        profile.setTown(params.get("town"));
        profileService.save(profile);
        return new ProfileDto(profile);
    }


//    @PostMapping(consumes = "application/json", produces = "application/json")
//    public ProfileDto changeProfile(@RequestBody Profile profile, @RequestParam String password, Principal principal){
//        String password = "100";
//
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principal.getName(), password));
//        } catch (BadCredentialsException ex) {
//            throw new WrongPasswordException("Wrong password");
//        }
//        profile.setUser(userService.findByUsername(principal.getName()).orElseThrow());
//        Profile p = profileService.save(profile);
//        return new ProfileDto(p);
//    }

//    @PostMapping
//    public ProfileDto changeProfile(@RequestBody TestDto testDto, Principal principal){
//        if(!testDto.getPassword().equals(encoder.encode(userService.findByUsername(principal.getName()).orElseThrow().getPassword()))){
//            throw new WrongPasswordException("Wrong password");
//        }
//        Profile prof = testDto.getProfile();
//        prof.setUser(userService.findByUsername(principal.getName()).orElseThrow());
//        prof = profileService.save(prof);
//        return new ProfileDto(prof);
//    }

}

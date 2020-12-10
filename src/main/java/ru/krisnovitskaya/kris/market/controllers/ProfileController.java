package ru.krisnovitskaya.kris.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.dto.ProfileDto;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.entities.User;
import ru.krisnovitskaya.kris.market.exceptions.MarketError;
import ru.krisnovitskaya.kris.market.exceptions.ResourceNotFoundException;
import ru.krisnovitskaya.kris.market.services.ProfileService;
import ru.krisnovitskaya.kris.market.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @GetMapping(produces = "application/json")
    public ProfileDto showProfile(Principal principal) {
        Profile p = profileService.findProfileByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user"));
        return new ProfileDto(p);
    }


    @PutMapping(produces = "application/json")
    public ResponseEntity<?> changeProfile(@RequestBody Profile profile, @RequestParam String password, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find current user"));
        if (password == null || !encoder.matches(password, currentUser.getPassword())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Incorrect password"), HttpStatus.BAD_REQUEST);
        }

        profile.setUser(currentUser);
        profileService.save(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

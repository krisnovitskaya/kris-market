package ru.krisnovitskaya.kris.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.entities.Role;
import ru.krisnovitskaya.kris.market.entities.User;
import ru.krisnovitskaya.kris.market.services.ProfileService;
import ru.krisnovitskaya.kris.market.services.RoleService;
import ru.krisnovitskaya.kris.market.services.UserService;


import java.util.HashSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reg")
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder encoder;



    @PostMapping(consumes = "application/json", produces = "application/json")
    public void saveNewUser(@RequestBody User user){
        Role role = roleService.findByName("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>() {{ add(role);}});
        Profile profile = new Profile();
        user.setProfile(profile);
        profile.setUser(user);
        userService.save(user);
    }
}

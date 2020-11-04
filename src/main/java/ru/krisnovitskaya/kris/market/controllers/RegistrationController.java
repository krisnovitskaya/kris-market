package ru.krisnovitskaya.kris.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.entities.Role;
import ru.krisnovitskaya.kris.market.entities.User;
import ru.krisnovitskaya.kris.market.services.RoleService;
import ru.krisnovitskaya.kris.market.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reg")
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder;


    @PostMapping
    public void trySaveNewUser(@RequestParam String username,@RequestParam String email, @RequestParam String password ){
        Role role = roleService.findByName("ROLE_USER");
        User user = new User(username, encoder.encode(password), email, role);
        userService.save(user);
    }
}

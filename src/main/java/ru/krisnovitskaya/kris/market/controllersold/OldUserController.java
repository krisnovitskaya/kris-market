package ru.krisnovitskaya.kris.market.controllersold;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.entities.Order;
import ru.krisnovitskaya.kris.market.entities.Role;
import ru.krisnovitskaya.kris.market.entities.User;
import ru.krisnovitskaya.kris.market.services.RoleService;
import ru.krisnovitskaya.kris.market.services.UserService;

import java.security.Principal;

//@Controller
//@RequestMapping("/signup")
//@AllArgsConstructor
public class OldUserController {
    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    public String signUp(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email
    ) {
        Role role = roleService.findByName("ROLE_USER");
        User user = new User(username, bCryptPasswordEncoder.encode(password), email, role);
        userService.save(user);
        return "redirect:/products";
    }

    @GetMapping
    public String signUp(){
        return "signup";
    }
}

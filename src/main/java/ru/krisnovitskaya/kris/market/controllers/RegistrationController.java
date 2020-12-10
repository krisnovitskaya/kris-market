package ru.krisnovitskaya.kris.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.dto.NewUserDto;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.entities.Role;
import ru.krisnovitskaya.kris.market.entities.User;
import ru.krisnovitskaya.kris.market.exceptions.RegistrationError;
import ru.krisnovitskaya.kris.market.services.ProfileService;
import ru.krisnovitskaya.kris.market.services.RoleService;
import ru.krisnovitskaya.kris.market.services.UserService;


import java.util.HashSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reg")
public class RegistrationController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody @Validated NewUserDto newUser, BindingResult bindingResult) {
        if (userService.findByUsername(newUser.getUsername()).isPresent()) {
            return new ResponseEntity<>(new RegistrationError("Username " + newUser.getUsername() + " is busy"), HttpStatus.BAD_REQUEST);
        }
        if (!newUser.getPassword().equals(newUser.getConfirmationPassword())) {
            return new ResponseEntity<>(new RegistrationError("Password and confirmed password isn't equal"), HttpStatus.BAD_REQUEST);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new RegistrationError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }
        userService.saveUserFromDtoAsEntity(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

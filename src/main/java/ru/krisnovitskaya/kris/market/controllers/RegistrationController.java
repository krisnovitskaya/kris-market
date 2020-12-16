package ru.krisnovitskaya.kris.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.dto.NewUserDto;
import ru.krisnovitskaya.kris.market.exceptions.RegistrationError;

import ru.krisnovitskaya.kris.market.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reg")
public class RegistrationController {
    private final UserService userService;


    /**
     * Check input data and create new user
     * @param newUser
     * @param bindingResult
     * @return HttpStatus
     */
    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody @Validated NewUserDto newUser, BindingResult bindingResult) {
        if(newUser.getUsername() == null || newUser.getEmail() == null || newUser.getPassword() == null || newUser.getConfirmationPassword() == null){
            return new ResponseEntity<>(new RegistrationError("Some fields are empty."), HttpStatus.BAD_REQUEST);
        }
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

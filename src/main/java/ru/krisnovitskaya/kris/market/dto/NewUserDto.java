package ru.krisnovitskaya.kris.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class NewUserDto {
    @Size(min = 4, message = "Username length must be at least 4 symbols")
    private String username;

    @Email(message = "E-mail format error")
    private String email;

    private String password;

    private String confirmationPassword;
}

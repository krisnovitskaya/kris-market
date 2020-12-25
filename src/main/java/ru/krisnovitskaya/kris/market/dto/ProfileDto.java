package ru.krisnovitskaya.kris.market.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.entities.User;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class ProfileDto {


    private String firstname;
    private String lastname;

    @Email(message = "wrong email format")
    private String email;

    @Positive(message = "number must be positive")
    private Long phone;

    private Integer birthYear;

    private Boolean sex;

    private String town;


    public ProfileDto(Profile profile) {
        this.firstname = profile.getFirstname();
        this.lastname = profile.getLastname();
        this.email = profile.getEmail();
        this.phone = profile.getPhone();
        this.birthYear = profile.getBirthYear();
        this.sex = profile.getSex();
        this.town = profile.getTown();
    }
}

package ru.krisnovitskaya.kris.market.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.entities.User;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class ProfileDto {

    private Long id;
    private String firstname;
    private String lastname;
    private int phone;
    private int birthYear;
    private String sex;
    private String town;


    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.firstname = profile.getFirstname();
        this.lastname = profile.getLastname();
        this.phone = profile.getPhone();
        this.birthYear = profile.getBirthYear();
        this.sex = profile.getSex();
        this.town = profile.getTown();
    }
}

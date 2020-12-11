package ru.krisnovitskaya.kris.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krisnovitskaya.kris.market.dto.ProfileDto;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private int phone;

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "town")
    private String town;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Profile(String firstname, String lastname, String email, int phone, int birthYear, Boolean sex, String town) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.birthYear = birthYear;
        this.sex = sex;
        this.town = town;
    }

    public Profile updateProfile(ProfileDto profileDto){
        this.setEmail(profileDto.getEmail());
        this.setBirthYear(profileDto.getBirthYear());
        this.setFirstname(profileDto.getFirstname());
        this.setLastname(profileDto.getLastname());
        this.setPhone(profileDto.getPhone());
        this.setSex(profileDto.getSex());
        this.setTown(profileDto.getTown());
        return this;
    }


}

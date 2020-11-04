package ru.krisnovitskaya.kris.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "phone")
    private int phone;

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "sex")
    private String sex;

    @Column(name = "town")
    private String town;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Profile(String firstname, String lastname, int phone, int birthYear, String sex, String town) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.birthYear = birthYear;
        this.sex = sex;
        this.town = town;
    }


}

package ru.krisnovitskaya.kris.market.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krisnovitskaya.kris.market.entities.Profile;

@Data
@NoArgsConstructor
public class TestDto {
    private Profile profile;
    private String password;
}

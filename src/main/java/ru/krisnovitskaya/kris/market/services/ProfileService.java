package ru.krisnovitskaya.kris.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.repositories.ProfileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Profile save(Profile profile){
        return profileRepository.save(profile);
    }


    public Optional<Profile> findProfileByUsername(String username){
        return profileRepository.findProfileByUsername(username);
    }
}

package ru.krisnovitskaya.kris.market;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.krisnovitskaya.kris.market.dto.ProfileDto;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.repositories.ProductRepository;
import ru.krisnovitskaya.kris.market.repositories.ProfileRepository;

@DataJpaTest
@ActiveProfiles("h2")
public class RepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;



    @Test
    public void checkFindProfileByUsername(){
        Profile profile = profileRepository.findProfileByUsername("user1");
        Assertions.assertNotNull(profile);
        Assertions.assertEquals(1980, profile.getBirthYear());
        System.out.println(new ProfileDto(profile));
    }


}

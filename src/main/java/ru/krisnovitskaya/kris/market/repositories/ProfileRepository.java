package ru.krisnovitskaya.kris.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.entities.User;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
    @Query("select p from Profile p where p.user.username = ?1")
    Profile findProfileByUsername(String username);
}

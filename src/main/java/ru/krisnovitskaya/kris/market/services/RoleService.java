package ru.krisnovitskaya.kris.market.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krisnovitskaya.kris.market.entities.Role;
import ru.krisnovitskaya.kris.market.repositories.RoleRepository;


@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}

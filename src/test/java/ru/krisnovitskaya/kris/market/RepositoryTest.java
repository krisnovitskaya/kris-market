package ru.krisnovitskaya.kris.market;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.krisnovitskaya.kris.market.dto.PageDto;
import ru.krisnovitskaya.kris.market.dto.ProductDto;
import ru.krisnovitskaya.kris.market.dto.ProfileDto;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.entities.Profile;
import ru.krisnovitskaya.kris.market.repositories.ProductRepository;
import ru.krisnovitskaya.kris.market.repositories.ProfileRepository;
import ru.krisnovitskaya.kris.market.utils.ProductFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@ActiveProfiles("h2")
public class RepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProductRepository productRepository;



    @Test
    public void checkFindProfileByUsername(){
        Profile profile = profileRepository.findProfileByUsername("user1");
        Assertions.assertNotNull(profile);
        Assertions.assertEquals(1980, profile.getBirthYear());
        System.out.println(new ProfileDto(profile));
    }


    @Test
    public void checkFindAllWithSpec(){
        ProductFilter filter = makeFilterWithSpec(null, 180, 199, List.of("category 1", "category 2"));
        Page<Product> content = productRepository.findAll(filter.getSpec(), PageRequest.of(0, 5));
        PageDto<ProductDto> pdto = new PageDto<>(content.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), content.getPageable(), content.getTotalElements());
        Assertions.assertNotNull(pdto);
        Assertions.assertNotNull(pdto.getContent());
        Assertions.assertEquals(4, pdto.getTotalElements());
    }

    private ProductFilter makeFilterWithSpec(String title, Integer min, Integer max, List<String> categories){

        MultiValueMap<String,String> valueMap = new LinkedMultiValueMap<>(){{
            if(title != null) put("title", List.of(title));
            if(min != null) put("min_price", List.of(min.toString()));
            if(max != null) put("max_price", List.of(max.toString()));
            if(categories != null) put("categories",categories);
        }};


        ProductFilter filter = new ProductFilter(valueMap);
        return filter;
    }


}

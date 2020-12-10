package ru.krisnovitskaya.kris.market;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.krisnovitskaya.kris.market.dto.PageDto;
import ru.krisnovitskaya.kris.market.dto.ProductDto;
import ru.krisnovitskaya.kris.market.entities.Category;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.exceptions.MarketError;
import ru.krisnovitskaya.kris.market.exceptions.ResourceNotFoundException;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FullServerRunByProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test()
    public void checkGetProductById() {
        ResponseEntity<Product> response = restTemplate.getForEntity("/api/v1/products/{id}", Product.class, 10L);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(10L, response.getBody().getId());
        Assertions.assertNotNull(response.getBody().getCategories());
    }

    @Test
    public void checkGetAllProductsAsPageDto() {
        PageDto<ProductDto> products = restTemplate.getForObject("/api/v1/products", PageDto.class);
        Assertions.assertNotNull(products);
        Assertions.assertNotNull(products.getContent());
        Assertions.assertEquals(5, products.getContent().size());
        Assertions.assertEquals(20, products.getTotalElements());
        Assertions.assertEquals(4, products.getTotalPages());
    }

    @Test
    public void checkDeleteAll(){
        restTemplate.delete("/api/v1/products");
        PageDto<ProductDto> products = restTemplate.getForObject("/api/v1/products", PageDto.class);
        Assertions.assertEquals(0, products.getTotalElements());
    }

    @Test
    public void checkDeleteById(){
        restTemplate.delete("/api/v1/products/{id}", 10L);
        ResponseEntity<?> entity = restTemplate.getForEntity("/api/v1/products/{id}", MarketError.class, 10L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }

}

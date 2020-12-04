package ru.krisnovitskaya.kris.market;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.LinkedMultiValueMap;
import ru.krisnovitskaya.kris.market.dto.PageDto;
import ru.krisnovitskaya.kris.market.dto.ProductDto;
import ru.krisnovitskaya.kris.market.entities.Category;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.repositories.ProductRepository;
import ru.krisnovitskaya.kris.market.services.ProductService;
import ru.krisnovitskaya.kris.market.utils.ProductFilter;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void checkFindAll(){
        Mockito.doReturn(getFakePageProduct())
                .when(productRepository)
                .findAll(new ProductFilter(new LinkedMultiValueMap<>()).getSpec(), PageRequest.of(0, 5));


        PageDto<ProductDto> pageProduct = productService.findAll(new ProductFilter(new LinkedMultiValueMap<>()).getSpec(), 0, 5);

        Assertions.assertNotNull(pageProduct);
        Assertions.assertEquals(5,pageProduct.getContent().size());
        Assertions.assertEquals("Carrot1",pageProduct.getContent().get(0).getTitle());

    }

    private Page<Product> getFakePageProduct() {
        Category category = new Category();
        category.setId(1L);
        category.setName("category 1");

        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Product p1 = new Product();
            p1.setId((long)i);
            p1.setPrice(i*100);
            p1.setTitle("Carrot" + i);
            p1.setCategories(List.of(category));

            products.add(p1);

        }

        return new PageImpl<>(products, PageRequest.of(0, 5), products.size());

    }


}

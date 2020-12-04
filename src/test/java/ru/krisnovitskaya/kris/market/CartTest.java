package ru.krisnovitskaya.kris.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.krisnovitskaya.kris.market.entities.OrderItem;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.services.ProductService;
import ru.krisnovitskaya.kris.market.utils.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = Cart.class)
public class CartTest {
    @Autowired
    private Cart cart;

    @MockBean
    private ProductService productService;

    private final List<Product> fakeProducts = makeFakeProductsForTest();


    @Test
    public void checkClear(){

        cart.getItems().add(new OrderItem());
        cart.getItems().add(new OrderItem());
        cart.getItems().add(new OrderItem());

        Assertions.assertEquals(3, cart.getItems().size());

        cart.clear();
        Assertions.assertEquals(0, cart.getItems().size());

    }

    @Test
    public void checkAddOrIncrement(){
        for (int i = 0; i < 5; i++) {
            Mockito.doReturn(Optional.of(fakeProducts.get(i)))
                    .when(productService).findById((long)(i +1));
        }

        cart.addOrIncrement(1L);
        cart.addOrIncrement(1L);
        Assertions.assertEquals(1, cart.getItems().size());
        Assertions.assertEquals(204, cart.getPrice());


        cart.addOrIncrement(3L);
        Assertions.assertEquals( 2, cart.getItems().size());
        Assertions.assertEquals( 310, cart.getPrice());
        Assertions.assertNotEquals( 300, cart.getPrice());
    }


    @Test
    public void checkDecrementOrRemove(){
        for (int i = 0; i < 5; i++) {
            Mockito.doReturn(Optional.of(fakeProducts.get(i)))
                    .when(productService).findById((long)(i +1));
        }

        cart.addOrIncrement(1L);
        cart.addOrIncrement(2L);
        cart.addOrIncrement(2L);
        cart.addOrIncrement(3L);
        cart.addOrIncrement(4L);
        cart.addOrIncrement(5L);
        cart.addOrIncrement(5L);

        int startCartPrice = cart.getPrice();

        //уменьшаем количество
        cart.decrementOrRemove(2L);
        Assertions.assertEquals(5, cart.getItems().size());
        Assertions.assertEquals(startCartPrice - fakeProducts.get(1).getPrice(), cart.getPrice());

        //удаляем
        cart.decrementOrRemove(2L);
        Assertions.assertEquals(4, cart.getItems().size());

        //пытаемся удалить то, чего нет
        cart.decrementOrRemove(2L);
        Assertions.assertEquals(4, cart.getItems().size());

    }



    private List<Product> makeFakeProductsForTest(){
        List<Product> fakeProducts = new ArrayList<>();

        for (long i = 0; i < 5; i++) {
            Product p = new Product();
            p.setId(i + 1);
            p.setPrice(100 + (int)(p.getId()*2));
            fakeProducts.add(p);
        }
        return fakeProducts;
    }
}

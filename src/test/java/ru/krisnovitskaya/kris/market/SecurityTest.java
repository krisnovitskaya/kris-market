package ru.krisnovitskaya.kris.market;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void checkAccessToCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void checkAccessToProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



    @Test
    @WithMockUser(username = "user1", roles = "USER")
    public void checkAccessToCart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cart"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @WithMockUser(username = "user1", roles = "USER")
    public void checkAccessToOrdersWithMockUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void checkAccessToOrdersWithOutMockUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}

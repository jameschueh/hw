package com.example.hw;

import com.example.hw.controller.ShoppingCartController.ProductRequest;
import com.example.hw.controller.ShoppingCartController.ShoppingCartRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGenerateReceipt() throws Exception {
        ShoppingCartRequest request = new ShoppingCartRequest();
        request.setStateCode("CA");
        request.setProducts(Arrays.asList(
                new ProductRequest("book", 1),
                new ProductRequest("potato chips", 1)
        ));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/cart/receipt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request)))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Receipt generated:");
        System.out.println(responseContent);
    }

    // Utility class to convert objects to JSON string
    private static class JsonUtil {
        public static String toJson(Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package com.example.productrestapi;

import com.example.productrestapi.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductrestapiApplicationTests {

    @Value("${productrestapi.services.url}")
    private String baseURL;

    @Test
    public void testGetProduct() {
        System.out.println(baseURL);
    RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(baseURL + "/3", Product.class);
        assertNotNull(product);
        assertEquals("Iphone", product.getName());
    }

    @Test
    public void testCreateProduct() {
        RestTemplate restTemplate = new RestTemplate();
        Product product = new Product();
        product.setName("Samsung Mobile");
        product.setDescription("cool");
        product.setPrice(800);
        Product newProduct = restTemplate.postForObject(baseURL, product, Product.class);
        assertNotNull(newProduct);
        assertNotNull(newProduct.getId());
        assertEquals("Samsung Mobile", newProduct.getName());
    }

    @Test
    public void testUpdateProduct() {
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(baseURL + "/3", Product.class);
        product.setPrice(5000);
        restTemplate.put(baseURL, product);
    }
}

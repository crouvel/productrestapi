package com.example.productrestapi.controllers;

import com.example.productrestapi.entities.Product;
import com.example.productrestapi.repos.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    @Autowired
    ProductRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    @RequestMapping(value="/products", method = RequestMethod.GET)
    public List<Product> getProducts(){
        return repository.findAll();
    }

    @RequestMapping(value="/products/{id}", method = RequestMethod.GET)
    public Product getProducts(@PathVariable("id") int id){
        LOGGER.info("finding product by id: " + id);
        return repository.findById(id).get();
    }

    @RequestMapping(value="/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @RequestMapping(value="/products", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @RequestMapping(value="/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") int id){
        repository.deleteById(id);
    }
}

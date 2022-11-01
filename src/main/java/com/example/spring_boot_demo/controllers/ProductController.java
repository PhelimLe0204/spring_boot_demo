package com.example.spring_boot_demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_demo.models.Product;
import com.example.spring_boot_demo.models.ResponseObject;
import com.example.spring_boot_demo.repositories.ProductRepository;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    // DI = Dependency Injection
    @Autowired
    private ProductRepository repository;

    /*------------------------
        1: Get all product
    ------------------------*/
    @GetMapping("")
    // This request is: http://localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
        return repository.findAll();
        // return List.of(
        // new Product(1L, "Macbook pro 16 inch", 2020, 2400.0, ""),
        // new Product(2L, "Ipad air green", 2021, 599.0, ""));
        // You must save this to Database. Now we have H2 DB = In-memory Database
        // You can also send request using postman
    }

    /*---------------------------
        2: Get detail product
    ---------------------------*/
    @GetMapping("/{id}")
    // Optional<Product> findById(@PathVariable Long id) {
    // return repository.findById(id);
    // }
    // Product findById(@PathVariable Long id) {
    // return repository.findById(id)
    // .orElseThrow(() -> new RuntimeException("Cannot find product with id: " +
    // id));
    // }
    // Let's return an object with: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);

        // if (foundProduct.isPresent()) {
        // return ResponseEntity.status(HttpStatus.OK).body(
        // new ResponseObject("Ok", "Query product successfully", foundProduct));
        // } else {
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        // new ResponseObject("failed", "Cannot find product with id: " + id,
        // foundProduct));
        // }

        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Query product successfully", foundProduct))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id: " + id, foundProduct));
    }

    /*--------------------------------------------
        3: Insert new product with POST method
        Postman: Raw, JSON
    --------------------------------------------*/
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        // 2 product must not have the same name!
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());

        if (foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product's name already taken", ""));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Insert product successfully", repository.save(newProduct)));
    }

    /*-----------------------------------------------------------
        4: Update, upsert = update if found, otherwise insert
    -----------------------------------------------------------*/
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = repository.findById(id).map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setYear(newProduct.getYear());
            product.setPrice(newProduct.getPrice());
            product.setUrl(newProduct.getUrl());
            return repository.save(product);
        }).orElseGet(() -> {
            newProduct.setId(id);
            return repository.save(newProduct);
        });

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Update product successfully!", updatedProduct));
    }
}

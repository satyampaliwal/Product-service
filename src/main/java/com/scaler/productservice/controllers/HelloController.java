//package com.scaler.productservice.controllers;
//
//import com.scaler.productservice.dto.ErrorResponseDTO;
//import com.scaler.productservice.exceptions.ProductNotFoundException;
//import com.scaler.productservice.models.Product;
//import com.scaler.productservice.services.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class HelloController {
//
//    @Autowired
//    @Qualifier("UjwalProductService")
//    ProductService productService;
//
//
//    @GetMapping("/hello")
//    public String sayHello() throws ProductNotFoundException {
//        throw new ProductNotFoundException("hello");
////        List<Product> productList = productService.getAllProducts();
////        return "hello from ujwal " + productList.size();
//    }
//}

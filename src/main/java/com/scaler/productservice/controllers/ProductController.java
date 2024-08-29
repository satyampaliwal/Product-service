package com.scaler.productservice.controllers;


import com.scaler.productservice.dto.*;
import com.scaler.productservice.exceptions.DBNotFoundException;
import com.scaler.productservice.exceptions.DBTimeoutException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("FakeStoreService")
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getSingleProduct(@PathVariable("id") String productId) throws DBTimeoutException, DBNotFoundException, ProductNotFoundException {
        Product product = productService.getSingleProduct(productId);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProduct(product);
        productResponseDTO.setResponseMessage("success");

        ResponseEntity<ProductResponseDTO> responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/products")
    public ResponseEntity<ListProductsResponseDTO>  getAllProducts(
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    )  {
        Page<Product> products = productService.getAllProducts(pageSize, pageNumber, sortField, sortOrder);

        ListProductsResponseDTO responseDTO = new ListProductsResponseDTO();
        responseDTO.setProductList(products);
        responseDTO.setResponseMessage("SUCCESS");
        ResponseEntity<ListProductsResponseDTO> responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/search")
    // url: GET     /search?catName=laptop
    public List<Product>  getProductsByCategoryName(@RequestParam("catName") String categoryName){
        List<Product> products = productService.getProductsByCategoryName(categoryName);
        return products;
    }

//    @GetMapping("/search")
//    // localhost:9000/products?text="hello"
//    public List<Product> searchProducts(@RequestParam("text") String queryText){
//        List<Product> products = productService.searchProducts(queryText);
//        return products;
//    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductDTO createProductDTO){
        Product toBeSavedProduct = createProductDTO.toProduct();
        Product savedProduct = productService.createProduct(toBeSavedProduct);
        return savedProduct;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException errorObject){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorMessage("from controller " + errorObject.getMessage());
        ResponseEntity<ErrorResponseDTO> responseEntity = new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}

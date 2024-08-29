package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.exceptions.DBNotFoundException;
import com.scaler.productservice.exceptions.DBTimeoutException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(String productId) throws ProductNotFoundException, DBNotFoundException, DBTimeoutException;

    Page<Product> getAllProducts(Integer pageSize, Integer pageNumber, String sortField, String sortOrder);

    List<Product> getProductsByCategoryName(String categoryName);

    List<Product> searchProducts(String searchText);

    // to create object in your own DB
    Product createProduct(Product product);

    // to create objects in FakeStore DB
    // not the right way to exactly.
    Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO);
}

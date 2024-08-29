package com.scaler.productservice.services;

import com.scaler.productservice.dto.ErrorResponseDTO;
import com.scaler.productservice.dto.FakeStorePOSTResponseDTO;
import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.dto.FakeStoreResponseDTO;
import com.scaler.productservice.exceptions.DBNotFoundException;
import com.scaler.productservice.exceptions.DBTimeoutException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("FakeStoreService")
public class FakeStoreProductService implements ProductService{

    // It has to hit the APIs of FakeStore server
    // we use RestTemplate to call 3rd party APIs
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Product getSingleProduct(String productId) throws ProductNotFoundException, DBNotFoundException, DBTimeoutException {
        Product cachedProduct = (Product) redisTemplate.opsForHash().get("Product", "PRODUCT_" + productId);
        if(cachedProduct != null){
            return cachedProduct;
        }

        FakeStoreResponseDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreResponseDTO.class
        );
        if(response == null){
            throw new ProductNotFoundException("product with id " + productId + " not found");
        }

        Product product = response.toProduct();
        redisTemplate.opsForHash().put("Product", "PRODUCT_" + productId, product);
        return product;
    }

    @Override
    public Page<Product> getAllProducts(Integer pageSize, Integer pageNumber, String sortField, String sortOrder) {
        List<Product> cachedProductList = (List<Product>) redisTemplate.opsForHash().get("Product", "PRODUCT_ALL");
        if(cachedProductList != null && cachedProductList.size() > 0){
            Page<Product> page = new PageImpl<>(cachedProductList);
            return page;
        }

        FakeStoreResponseDTO[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreResponseDTO[].class
        );
        List<Product> productsList = new ArrayList<>();
        for(FakeStoreResponseDTO response: responseArray){
            Product product = response.toProduct();
            productsList.add(product);
        }
        redisTemplate.opsForHash().put("Product", "PRODUCT_ALL", productsList);
        Page<Product> page = new PageImpl<>(productsList);
        return page;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        return List.of();
    }

    @Override
    public List<Product> searchProducts(String searchText) {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        FakeStorePOSTResponseDTO savedProductResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreRequestDTO,
                FakeStorePOSTResponseDTO.class
        );

        Product product = savedProductResponse.toProduct();
        return product;
    }
}

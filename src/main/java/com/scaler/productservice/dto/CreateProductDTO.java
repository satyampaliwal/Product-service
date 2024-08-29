package com.scaler.productservice.dto;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDTO {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Double discount;
    private String categoryName;

    public Product toProduct() {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setDiscount(discount);
        Category category = new Category();
        category.setName(categoryName);
        product.setCategory(category);
        return product;
    }

}

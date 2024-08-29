package com.scaler.productservice.dto;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStorePOSTResponseDTO {
    private String id;
    private String title;
    private Integer price;
    private String description;
    private String image;
    private String category;
    private String rating;
    private String comments;

    public Product toProduct(){
        Product product = new Product();
        product.setId(Long.valueOf(this.id));
        product.setName(this.title);
        product.setDescription(this.description);
        product.setImageUrl(this.image);
        product.setPrice(this.price * 1.0);
        Category category = new Category();
        category.setName(this.category);
//        product.setCategory(category);
        // ...
        // ...
        return product;
    }
}

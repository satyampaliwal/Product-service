package com.scaler.productservice.dto;

import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
@Setter
public class ListProductsResponseDTO {
    private Page<Product> productList;
    private String responseMessage;
}

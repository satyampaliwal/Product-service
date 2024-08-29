package com.scaler.productservice.dto;

import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Product product;
    private String responseMessage;
}

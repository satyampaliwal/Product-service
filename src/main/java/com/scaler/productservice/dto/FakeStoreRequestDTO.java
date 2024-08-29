package com.scaler.productservice.dto;

import lombok.Getter;
import lombok.Setter;

// its purpose to accept request from FE
@Getter
@Setter
public class FakeStoreRequestDTO {
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}

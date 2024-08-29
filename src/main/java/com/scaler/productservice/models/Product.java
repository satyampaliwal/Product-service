package com.scaler.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel implements Serializable {
    // all these are primitive data types
    // int, String, Long, double.
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Double discount;
    // This is non-primitive
    // I have to define a relation between product and category
    // => Cardinality.
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Category category;  // eager fetch, by default.
}

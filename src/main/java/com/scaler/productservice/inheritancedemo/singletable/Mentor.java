package com.scaler.productservice.inheritancedemo.singletable;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("1")
public class Mentor extends User {
    private String company;
    private Float avgRating;
}

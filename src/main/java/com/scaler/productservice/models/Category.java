package com.scaler.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseModel implements Serializable {
    private String name;

//    // it is the inverse of the same relation between product and category
//    @OneToMany(mappedBy = "category")
//    // this relation is already handled by the category column, in the Product table
//    private List<Product> products;
//    // default behaviour for list -> lazy

}

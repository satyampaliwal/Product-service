package com.scaler.productservice.repository;


import com.scaler.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);

    Optional<Category> getCategoryByName(String catName);

    List<Category> getCategoriesByNameContaining(String text);
}

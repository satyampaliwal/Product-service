package com.scaler.productservice.repository;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long>{

    // declared queries
    Product save(Product product);

    List<Product> getProductsByCategoryIn(List<Category> categories);

    @Query(value = "select p.* from product p where p.category_id IN :categoryIds", nativeQuery = true)
    List<Product> useSQLToGetProductsByCategoryIn(@Param("categoryIds") List<Long> categoryIds);

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long id);

    Integer countProductByPriceLessThan(Double value);

    List<Product> getProductByNameContaining(String text);
    // select count(*) from products p where p.price < value;

    List<Product> getProductByNameLikeAndDescriptionLikeOrderByPriceDesc(String nameText, String descText);
    List<Product> getProductByCategoryName(String categoryName);
    // select * from products p JOIN category c on p.category_id = c.id and c.name = categoryName

    void deleteProductById(Long id);    // hard delete -> permanent deletion of the row.


    @Query("select p from Product p")
    List<Product> getAllProducts();

    @Query("select p from Product p where p.category.name = :categoryName")
    List<Product> allProductsGivenCatNameUsingHQL(@Param("categoryName") String catName);

    @Query(value = "select p.* from product p LEFT JOIN category c ON p.category_id = c.id where c.name = :categoryName", nativeQuery = true)
    List<Product> properSQLQuery(@Param("categoryName") String catName);

    // in HQL, always give alias for Projections.
    @Query(value = "select p.id, p.name from product p LEFT JOIN category c ON p.category_id = c.id where c.name = :categoryName", nativeQuery = true)
    List<ProductProjection> fetchUsingProjection(@Param("categoryName") String catName);

    @Query(value = "select p from Product p where p.category.name = :categoryName")
    List<ProductProjection> fetchUsingProjectionHQL(@Param("categoryName") String catName);

}

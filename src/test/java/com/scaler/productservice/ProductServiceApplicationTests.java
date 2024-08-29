//package com.scaler.productservice;
//
//import com.scaler.productservice.models.Category;
//import com.scaler.productservice.models.Product;
//import com.scaler.productservice.projections.ProductProjection;
//import com.scaler.productservice.repository.CategoryRepository;
//import com.scaler.productservice.repository.ProductRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//class ProductServiceApplicationTests {
//
//    ProductRepository productRepository;
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    public ProductServiceApplicationTests(ProductRepository productRepository, CategoryRepository categoryRepository){
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//    }
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void testAllProductsUsingHQL(){
//        List<Product> productList = productRepository.getAllProducts();
//        for(Product product: productList){
//            System.out.println(product.getName());
//        }
//    }
//
//    @Test
//    void testAllProductsUsingHQLAndCatName(){
//        List<Product> productList = productRepository.allProductsGivenCatNameUsingHQL("laptop");
//        for(Product product: productList){
//            System.out.println(product.getName());
//        }
//    }
//
//    @Test
//    void sqlChalaLo(){
//        List<Product> productList = productRepository.properSQLQuery("laptop");
//        for(Product product: productList){
//            System.out.println(product.getName());
//        }
//    }
//
////    @Test
////    void sqlFetchUsingProjection(){
////        List<ProductProjection> productList = productRepository.fetchUsingProjection("laptop");
////        for(ProductProjection projection: productList){
////            System.out.println(projection.getId() + " " + projection.getName());
////        }
////    }
//
//    @Test
//    void hqlFetchUsingProjection(){
//        List<ProductProjection> productList = productRepository.fetchUsingProjectionHQL("laptop");
//        for(ProductProjection projection: productList){
//            System.out.println(projection.getId() + " " + projection.getName());
//        }
//    }
//
//
//    @Test
//    void fetchCategoryAndListOfProducts(){
//        Optional<Category> categoryOptional = categoryRepository.getCategoryByName("laptop");
//        if(categoryOptional.isEmpty()){
//            return;
//        }
//        Category category = categoryOptional.get();
//        System.out.println(category.getName());
//    }
//
//    @Test
//    void fetchProduct(){
//        Optional<Product> productOptional = productRepository.findById(1L);
//        if(productOptional.isEmpty()){
//            return;
//        }
//        Product product = productOptional.get();
//        System.out.println(product.getName());
//    }
//
//
//    @Test
//    void fetchProductsLazilyP2(){
//        Optional<Category> categoryOptional = categoryRepository.getCategoryByName("laptop");
//        if(categoryOptional.isEmpty()){
//            return;
//        }
//        Category category = categoryOptional.get();
//        System.out.println(category.getName());
//
//        List<Product> products = category.getProducts();
//        System.out.println(products.size());
//        for(Product product: products){
//            System.out.println(product.getName());
//        }
//    }
//
//
//    @Test
//    void testNPlusOneProblem(){
//        List<Category> categories = categoryRepository.getCategoriesByNameContaining("laptop");
//        // you make 1 single DB call -> N Categories
//        for(Category category: categories) {
//            System.out.println(category.getName());
//
//            List<Product> products = category.getProducts();
//            // for each Category -> You make another DB call
//            System.out.println(products.size());
//            for (Product product : products) {
//                System.out.println(product.getName());
//            }
//        }
//    }
//
//    // optimally, we can get all the Products in a single call as well
//    // select * from product p where p.category_id IN [category_list]
//
//    @Test
//    void testNPlusOneSolution(){
//        List<Category> categories = categoryRepository.getCategoriesByNameContaining("laptop");
//        // you make 1 single DB call -> N Categories
//        List<Product> productList = productRepository.getProductsByCategoryIn(categories);
//        for(Product product: productList) {
//            System.out.println(product.getName());
//        }
//    }
//
//    @Test
//    void testNPlusOneSolutionUsingSQL(){
//        List<Category> categories = categoryRepository.getCategoriesByNameContaining("laptop");
//        // you make 1 single DB call -> N Categories
//        List<Long> cIds = new ArrayList<>();
//        for(Category category: categories) {
//            cIds.add(category.getId());
//        }
//        // fetch all products, where p.category_id IN [cIds]
//        List<Product> productList = productRepository.useSQLToGetProductsByCategoryIn(cIds);
//        for(Product product: productList) {
//            System.out.println(product.getName());
//        }
//    }
//}

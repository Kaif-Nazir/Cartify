package com.shoppingcart.cartify.repository;

import com.shoppingcart.cartify.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryName(String name);
    List<Product> findByName(String name);
    List<Product> findByBrand(String brand);
    List<Product> findByCategory_NameAndBrand(String name , String brand);
    Long countByBrand(String brand);

}

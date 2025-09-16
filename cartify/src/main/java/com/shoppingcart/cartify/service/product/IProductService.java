package com.shoppingcart.cartify.service.product;

import com.shoppingcart.cartify.dto.ProductDto;
import com.shoppingcart.cartify.model.Product;
import com.shoppingcart.cartify.request.AddProductRequest;
import com.shoppingcart.cartify.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest request);
    Product getProductById(long id);
    void deleteProductById(long id);
    Product updateProduct(ProductUpdateRequest request , long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByCategoryAndBrand(String category , String brand);
    Long countProductsByBrand(String brand);

    List<ProductDto> getConvertedProducts(List<Product> products);
    ProductDto convertToDto(Product product);

}

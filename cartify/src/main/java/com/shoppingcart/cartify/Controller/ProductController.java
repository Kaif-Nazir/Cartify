package com.shoppingcart.cartify.Controller;

import com.shoppingcart.cartify.dto.ProductDto;
import com.shoppingcart.cartify.exception.ProductNotFoundException;
import com.shoppingcart.cartify.model.Product;
import com.shoppingcart.cartify.request.AddProductRequest;
import com.shoppingcart.cartify.request.ProductUpdateRequest;
import com.shoppingcart.cartify.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productService.getConvertedProducts(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id) {
        return ResponseEntity.ok(productService.convertToDto(productService.getProductById(id)));
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryName(@PathVariable String categoryName) {
        List<Product> products = productService.getProductsByCategoryName(categoryName);
        if (products.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productService.getConvertedProducts(products));
    }

    @GetMapping("/brand/{brandName}")
    public ResponseEntity<List<ProductDto>> getProductsByBrand(@PathVariable String brandName) {
        List<Product> products = productService.getProductsByBrand(brandName);
        if (products.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productService.getConvertedProducts(products));
    }

    @GetMapping("/category/{name}/brand/{brand}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndBrand(@PathVariable String name, @PathVariable String brand) {
        List<Product> products = productService.getProductsByCategoryAndBrand(name, brand);
        if (products.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productService.getConvertedProducts(products));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductDto>> getProductsByName(@PathVariable String name) {
        List<Product> products = productService.getProductsByName(name);
        if (products.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productService.getConvertedProducts(products));
    }

    @GetMapping("/count/brand/{brand}")
    public ResponseEntity<Long> countProductsByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(productService.countProductsByBrand(brand));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody AddProductRequest request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
            try{
                productService.deleteProductById(id);
                return ResponseEntity.noContent().build();
            } catch (ProductNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
    }
    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable long id){

        Product product = productService.updateProduct(request , id);

        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.convertToDto(product));
    }
}
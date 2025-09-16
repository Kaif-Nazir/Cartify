package com.shoppingcart.cartify.service.product;

import com.shoppingcart.cartify.dto.ProductDto;
import com.shoppingcart.cartify.exception.ProductNotFoundException;
import com.shoppingcart.cartify.model.Category;
import com.shoppingcart.cartify.model.Product;
import com.shoppingcart.cartify.repository.CategoryRepository;
import com.shoppingcart.cartify.repository.ProductRepository;
import com.shoppingcart.cartify.request.AddProductRequest;
import com.shoppingcart.cartify.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor  // Lombok direct for autowired
public class ProductService  implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void deleteProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with id " + id));
        productRepository.deleteById(product.getId());
    }

    @Override
    public Product addProduct(AddProductRequest request) {

        if(productRepository.existsByNameAndBrand(request.getName() , request.getBrand()))
            throw new IllegalStateException("Product Already Exists with Name :" + request.getName() + " Brand " + request.getBrand());

        Category category = Optional.ofNullable(
                        categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> categoryRepository.save(new Category(request.getCategory().getName())));


        Product product = new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );

        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(ProductUpdateRequest request, long productId) {

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        updateExistingProduct(existingProduct , request);

        return productRepository.save(existingProduct);
    }

    private void updateExistingProduct(Product existingProduct , ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).
                orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    public List<Product> getProductsByCategoryName(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategory_NameAndBrand(category , brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Long countProductsByBrand(String brand) {
        return productRepository.countByBrand(brand );
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);

    }
}

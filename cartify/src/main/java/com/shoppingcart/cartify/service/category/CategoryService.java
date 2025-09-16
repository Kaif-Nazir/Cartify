package com.shoppingcart.cartify.service.category;

import com.shoppingcart.cartify.exception.CategoryNotFoundException;
import com.shoppingcart.cartify.exception.CategoryAlreadyExist;
import com.shoppingcart.cartify.model.Category;
import com.shoppingcart.cartify.repository.CategoryRepository;
import com.shoppingcart.cartify.request.CategoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
            if(category == null){
                throw new CategoryNotFoundException("Category Not Found");
            }
            return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {

        Category existing = categoryRepository.findByName(category.getName());
        if (existing != null) {
            throw new CategoryAlreadyExist("Category already exists with name: " + category.getName());
        }
        return categoryRepository.save(category);
    }
    @Override
    public Category updateCategory(CategoryUpdateRequest category , long id) {

        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> {return new CategoryNotFoundException("Category not found with id" );});

        if(category.getName() != null){
            existing.setName(category.getName());
        }
        return categoryRepository.save(existing);
    }

    @Override
    public void deleteCategoryById(long id) {
        Category category = categoryRepository.findById(id)
                            .orElseThrow(() -> new CategoryNotFoundException("Category Not Found To Delete"));
        categoryRepository.delete(category);
    }
}

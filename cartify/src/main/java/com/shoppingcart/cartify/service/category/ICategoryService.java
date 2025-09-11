package com.shoppingcart.cartify.service.category;

import com.shoppingcart.cartify.model.Category;
import com.shoppingcart.cartify.request.CategoryUpdateRequest;

import java.util.List;

public interface ICategoryService {


    Category getCategoryById(long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategory();
    Category addCategory(Category category);
    Category updateCategory(CategoryUpdateRequest category , long id);
    void deleteCategoryById(long id);

}

package com.shoppingcart.cartify.Controller;


import com.shoppingcart.cartify.model.Category;
import com.shoppingcart.cartify.request.CategoryUpdateRequest;
import com.shoppingcart.cartify.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){

        List<Category> allCategories = categoryService.getAllCategories();
        if(allCategories != null && !allCategories.isEmpty()){
            return new ResponseEntity<>(allCategories , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoriesById(@PathVariable long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoriesByName(@PathVariable String name){

        Category category = categoryService.getCategoryByName(name);

        if(category == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return new ResponseEntity<>(category , HttpStatus.OK);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable long id){
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id , @RequestBody CategoryUpdateRequest category){

        Category oldCategory = categoryService.updateCategory(category , id);

        if(oldCategory == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(oldCategory , HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Category> addCategory(@RequestBody Category category){

        return new ResponseEntity<>(categoryService.addCategory(category) , HttpStatus.CREATED);

    }
}

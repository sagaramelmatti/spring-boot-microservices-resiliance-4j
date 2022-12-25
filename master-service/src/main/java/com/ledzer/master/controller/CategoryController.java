package com.ledzer.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ledzer.master.entity.Category;
import com.ledzer.master.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	Logger logger= LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/")
    public List<Category> categoryList(@RequestParam Map<String, String> parameters) {
        return categoryService.categoryList(parameters);
    }
	
	@GetMapping("/{id}")
    public Category getCategoryDetails(@PathVariable Long id){
        Category category = categoryService.getCategoryDetails(id);
        return category;
    }
	
	@PostMapping("/")
    public void saveCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
    }
	
	@PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category category){
        return categoryService.updateCategory(categoryId,category);
    }
	
	
	@DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
	
}

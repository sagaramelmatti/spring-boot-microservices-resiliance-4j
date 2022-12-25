package com.ledzer.master.service;

import java.util.List;
import java.util.Map;

import com.ledzer.master.entity.Category;

public interface CategoryService {

	public List<Category> categoryList(Map<String, String> parameters);

	public void saveCategory(Category category);

	public Category getCategoryDetails(Long id);

	public Category updateCategory(Long categoryId, Category category);

	public void deleteCategory(Long categoryId);

}

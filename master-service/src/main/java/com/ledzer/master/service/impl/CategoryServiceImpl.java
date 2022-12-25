package com.ledzer.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ledzer.master.utility.UtilityMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledzer.master.entity.Category;
import com.ledzer.master.repository.CategoryRepository;
import com.ledzer.master.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CategoryServiceImpl implements CategoryService {

    //private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> categoryList(Map<String, String> parameters) {
        logger.info("Order-Service Request : categoryList called");
        Pageable pageable = UtilityMethods.generatePage(parameters.get("limit"), parameters.get("offset"));
        ///return categoryRepository.findAll(pageable);
        Page<Category> pagedResult = categoryRepository.findAll(pageable);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList();
        }
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);

    }

    @Cacheable(cacheNames = "customer",key="#id")
    public Category getCategoryDetails(Long id) {
        return categoryRepository.findById(id).get();
    }

    public Category updateCategory(Long categoryId, Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}
}

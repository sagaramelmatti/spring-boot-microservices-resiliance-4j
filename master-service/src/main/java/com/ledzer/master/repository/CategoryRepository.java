package com.ledzer.master.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ledzer.master.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}

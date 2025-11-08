package com.project.artgallery.service;

import java.util.List;

import com.project.artgallery.payload.CategoryDTO;

public interface CategoryService {
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	List<CategoryDTO> getAllCategories();
	CategoryDTO getCategoryById(Long id);
	CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
	void deleteCategory(Long id);
}

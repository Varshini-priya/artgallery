package com.project.artgallery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.artgallery.entity.Category;
import com.project.artgallery.exception.ResourceNotFoundException;
import com.project.artgallery.payload.CategoryDTO;
import com.project.artgallery.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	private static final Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll().stream().map((cat)->modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryById(Long id) {
		Category cat = categoryRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Category not found with id: "+id));
		return modelMapper.map(cat, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
		Category cat = categoryRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Category not found with id: "+id));
		cat.setCategoryName(categoryDTO.getCategoryName());
		return modelMapper.map(categoryRepository.save(cat), CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
		logger.debug("Category deleted by the id: "+id);
	}

}

package com.project.artgallery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.artgallery.payload.ArtDTO;
import com.project.artgallery.payload.CategoryDTO;
import com.project.artgallery.service.ArtService;
import com.project.artgallery.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
    CategoryService categoryService;
	
	@Autowired
	ArtService artService;
	
	@GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> createCategory() {
		List<CategoryDTO> list=categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDTO>>(list, HttpStatus.OK);
    }
	
	@GetMapping("/categories/{categoryId}/art")
    public ResponseEntity<List<ArtDTO>> getAllArtsByCategoryId(@PathVariable Long categoryId) {
		List<ArtDTO> list=artService.getAllArtsByCategoryId(categoryId);
        return new ResponseEntity<List<ArtDTO>>(list, HttpStatus.OK);
    }
	
}

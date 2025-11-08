package com.project.artgallery.payload;

import java.util.List;

public class CategoryDTO {
	private Long categoryId;
	private String categoryName;
	private List<ArtDTO> arts;
	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryDTO(Long categoryId, String categoryName, List<ArtDTO> arts) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.arts = arts;
	}

	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ArtDTO> getArts() {
		return arts;
	}

	public void setArts(List<ArtDTO> arts) {
		this.arts = arts;
	}

}

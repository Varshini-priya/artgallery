package com.project.artgallery.payload;

import java.time.LocalDateTime;

import com.project.artgallery.entity.Artist;
import com.project.artgallery.entity.Category;

public class ArtDTO {
	private Long artId;
	private String artName;
	private Artist artist;
	private String image;
	private String description;
	private double price;
	private Category category;
	private LocalDateTime createdDate;
	private boolean status;
	
	public ArtDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArtDTO(Long artId, String artName, Artist artist, String image, String description, double price,
			Category category, LocalDateTime createdDate) {
		super();
		this.artId = artId;
		this.artName = artName;
		this.artist = artist;
		this.image = image;
		this.description = description;
		this.price = price;
		this.category = category;
		this.createdDate = createdDate;
	}
	
	

	public ArtDTO(Long artId, String artName, Artist artist, String image, String description, double price,
			Category category, LocalDateTime createdDate, boolean status) {
		super();
		this.artId = artId;
		this.artName = artName;
		this.artist = artist;
		this.image = image;
		this.description = description;
		this.price = price;
		this.category = category;
		this.createdDate = createdDate;
		this.status = status;
	}

	public Long getArtId() {
		return artId;
	}
	public void setArtId(Long artId) {
		this.artId = artId;
	}
	public String getArtName() {
		return artName;
	}
	public void setArtName(String artName) {
		this.artName = artName;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}

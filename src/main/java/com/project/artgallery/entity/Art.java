package com.project.artgallery.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Art {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artId;
	private String artName;
	@ManyToOne
	@JoinColumn(name="artist_id")
	private Artist artist;
	private String image;
	private String description;
	private double price;
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	private LocalDateTime createdDate;
	private boolean status;
	
	public Art() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Art(String artName, String image, String description, Category category) {
		super();
		this.artName = artName;
		this.image = image;
		this.description = description;
		this.category = category;
	}

	public Art(Long artId, String artName, Artist artist, String image, String description, Category category) {
		super();
		this.artId = artId;
		this.artName = artName;
		this.artist = artist;
		this.image = image;
		this.description = description;
		this.category = category;
	}
	
	
	public Art(Long artId, String artName, Artist artist, String image, String description, double price,
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

	public Art(Long artId, String artName, Artist artist, String image, String description, double price,
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Art [artId=" + artId + ", artName=" + artName + ", artist=" + artist + ", image=" + image
				+ ", description=" + description + ", price=" + price + ", category=" + category + ", createdDate="
				+ createdDate + "]";
	}
	
}

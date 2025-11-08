package com.project.artgallery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ArtFav {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artFavId;
	@ManyToOne
	@JoinColumn(name="art_id")
	private Art art;
	@ManyToOne
	@JoinColumn(name="fav_id")
	private FavoriteList favList;
	
	public ArtFav() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtFav(Long artFavId, Art art, FavoriteList favList) {
		super();
		this.artFavId = artFavId;
		this.art = art;
		this.favList = favList;
	}

	public Long getArtFavId() {
		return artFavId;
	}

	public void setArtFavId(Long artFavId) {
		this.artFavId = artFavId;
	}

	public Art getArt() {
		return art;
	}

	public void setArt(Art art) {
		this.art = art;
	}

	public FavoriteList getFavList() {
		return favList;
	}

	public void setFavList(FavoriteList favList) {
		this.favList = favList;
	}
	
	
}

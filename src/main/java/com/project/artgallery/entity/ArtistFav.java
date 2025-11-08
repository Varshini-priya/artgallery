package com.project.artgallery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ArtistFav {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artistFavId;
	@ManyToOne
	@JoinColumn(name="artist_id")
	private Artist artist;
	@ManyToOne
	@JoinColumn(name="fav_id")
	private FavoriteList favList;
	
	public ArtistFav() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtistFav(Long artistFavId, Artist artist, FavoriteList favList) {
		super();
		this.artistFavId = artistFavId;
		this.artist = artist;
		this.favList = favList;
	}

	public Long getArtistFavId() {
		return artistFavId;
	}

	public void setArtistFavId(Long artistFavId) {
		this.artistFavId = artistFavId;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public FavoriteList getFavList() {
		return favList;
	}

	public void setFavList(FavoriteList favList) {
		this.favList = favList;
	}
	
}

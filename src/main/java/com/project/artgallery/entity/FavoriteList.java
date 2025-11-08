package com.project.artgallery.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class FavoriteList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long favId;
	@OneToOne
	@JoinColumn(name="user_id")
	private Users user;
	@OneToMany(mappedBy = "favList", cascade = CascadeType.ALL)
	private List<ArtFav> artFav;
	@OneToMany(mappedBy = "favList", cascade = CascadeType.ALL)
	private List<ArtistFav> artistFav;
	public Long getFavId() {
		return favId;
	}
	public void setFavId(Long favId) {
		this.favId = favId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public List<ArtFav> getArtFav() {
		return artFav;
	}
	public void setArtFav(List<ArtFav> artFav) {
		this.artFav = artFav;
	}
	public List<ArtistFav> getArtistFav() {
		return artistFav;
	}
	public void setArtistFav(List<ArtistFav> artistFav) {
		this.artistFav = artistFav;
	}
	public FavoriteList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FavoriteList(Long favId, Users user, List<ArtFav> artFav, List<ArtistFav> artistFav) {
		super();
		this.favId = favId;
		this.user = user;
		this.artFav = artFav;
		this.artistFav = artistFav;
	}
	
	
}

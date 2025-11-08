package com.project.artgallery.payload;

import java.util.List;

public class FavoriteListDTO {
	private Long favId;
	private UsersDTO user;
	private List<ArtFavDTO> artFav;
	private List<ArtistFavDTO> artistFav;
	public FavoriteListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FavoriteListDTO(Long favId, UsersDTO user, List<ArtFavDTO> artFav, List<ArtistFavDTO> artistFav) {
		super();
		this.favId = favId;
		this.user = user;
		this.artFav = artFav;
		this.artistFav = artistFav;
	}
	public Long getFavId() {
		return favId;
	}
	public void setFavId(Long favId) {
		this.favId = favId;
	}
	public UsersDTO getUser() {
		return user;
	}
	public void setUser(UsersDTO user) {
		this.user = user;
	}
	public List<ArtFavDTO> getArtFav() {
		return artFav;
	}
	public void setArtFav(List<ArtFavDTO> artFav) {
		this.artFav = artFav;
	}
	public List<ArtistFavDTO> getArtistFav() {
		return artistFav;
	}
	public void setArtistFav(List<ArtistFavDTO> artistFav) {
		this.artistFav = artistFav;
	}
	
}

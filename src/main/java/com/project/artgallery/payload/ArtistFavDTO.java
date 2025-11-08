package com.project.artgallery.payload;


public class ArtistFavDTO {
	private Long artistFavId;
	private ArtistDTO artist;
	private FavoriteListDTO favList;
	public ArtistFavDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArtistFavDTO(Long artistFavId, ArtistDTO artist, FavoriteListDTO favList) {
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
	public ArtistDTO getArtist() {
		return artist;
	}
	public void setArtist(ArtistDTO artist) {
		this.artist = artist;
	}
	public FavoriteListDTO getFavList() {
		return favList;
	}
	public void setFavList(FavoriteListDTO favList) {
		this.favList = favList;
	}
}

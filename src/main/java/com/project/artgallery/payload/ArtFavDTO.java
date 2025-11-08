package com.project.artgallery.payload;

public class ArtFavDTO {
	private Long artFavId;
	private ArtDTO art;
	private FavoriteListDTO favList;
	public ArtFavDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArtFavDTO(Long artFavId, ArtDTO art, FavoriteListDTO favList) {
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
	public ArtDTO getArt() {
		return art;
	}
	public void setArt(ArtDTO art) {
		this.art = art;
	}
	public FavoriteListDTO getFavList() {
		return favList;
	}
	public void setFavList(FavoriteListDTO favList) {
		this.favList = favList;
	}
}

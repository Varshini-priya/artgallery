package com.project.artgallery.payload;

import java.util.Set;

import com.project.artgallery.entity.AppRole;

public class UsersDTO {
	private Long userId;
	private String userName;
	private String email;
	private String password;
	private Set<AppRole> roles;
	private ArtistDTO artist;
	private FavoriteListDTO favList;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<AppRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<AppRole> roles) {
		this.roles = roles;
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
	public UsersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsersDTO(Long userId, String userName, String email, String password, Set<AppRole> roles, ArtistDTO artist,
			FavoriteListDTO favList) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.artist = artist;
		this.favList = favList;
	}
}

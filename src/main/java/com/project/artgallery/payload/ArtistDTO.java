package com.project.artgallery.payload;

import java.util.List;


public class ArtistDTO {
	private Long artistId;
	private String artistName;
	private String about;
	private String profilePicture;
	private UsersDTO user;
	private List<ArtDTO> artList;
	
	public ArtistDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtistDTO(Long artistId, String artistName, String about, String profilePicture, UsersDTO user) {
		super();
		this.artistId = artistId;
		this.artistName = artistName;
		this.about = about;
		this.profilePicture = profilePicture;
		this.user = user;
	}
	

	public ArtistDTO(Long artistId, String artistName, String about, String profilePicture, UsersDTO user,
			List<ArtDTO> artList) {
		super();
		this.artistId = artistId;
		this.artistName = artistName;
		this.about = about;
		this.profilePicture = profilePicture;
		this.user = user;
		this.artList = artList;
	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public UsersDTO getUser() {
		return user;
	}

	public void setUser(UsersDTO user) {
		this.user = user;
	}

	public List<ArtDTO> getArtList() {
		return artList;
	}

	public void setArtList(List<ArtDTO> artList) {
		this.artList = artList;
	}
	
}

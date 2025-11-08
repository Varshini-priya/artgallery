package com.project.artgallery.entity;

import java.util.ArrayList;
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
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artistId;
	private String artistName;
	private String about;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private Users user;
	
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	private List<Art> artList = new ArrayList<>();
	
	private String profilePicture;

	public Artist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Artist(Long artistId, String artistName, String about, Users user, List<Art> artList,
			String profilePicture) {
		super();
		this.artistId = artistId;
		this.artistName = artistName;
		this.about = about;
		this.user = user;
		this.artList = artList;
		this.profilePicture = profilePicture;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Art> getArtList() {
		return artList;
	}

	public void setArtList(List<Art> artList) {
		this.artList = artList;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", artistName=" + artistName + ", about=" + about + ", user=" + user
				+ ", artList=" + artList + ", profilePicture=" + profilePicture + "]";
	}
	
	
}

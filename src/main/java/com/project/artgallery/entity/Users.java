package com.project.artgallery.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
})
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotBlank
	@Size(max = 20)
	@Column(name = "username")
	private String userName;

	@NotBlank
	@Size(max = 50)
	@Email
	@Column(unique = true,nullable = false)
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<AppRole> roles = Set.of(AppRole.ROLE_USER);
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Artist artist;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private FavoriteList favList;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(Long userId, @NotBlank @Size(max = 20) String userName, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 100) String password, Set<AppRole> roles, Artist artist, FavoriteList favList) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.artist = artist;
		this.favList = favList;
	}

	public Users(@NotBlank @Size(max = 20) String userName, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 100) String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

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

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", roles=" + roles + ", artist=" + artist + ", favList=" + favList + "]";
	}

	
	
}

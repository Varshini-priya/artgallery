package com.project.artgallery.payload;

import java.util.List;

public class UserInfoResponse {
	private long id;
	private String username;
	private String jwtToken;
	
	private String email;
	private List <String> roles;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public UserInfoResponse(long id, String username, String jwtToken, String email, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.jwtToken = jwtToken;
		this.email = email;
		this.roles = roles;
	}
	public UserInfoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfoResponse(long id, String username, String email, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
	

}

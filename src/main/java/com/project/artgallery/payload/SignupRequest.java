package com.project.artgallery.payload;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest ( 
		
	@NotBlank @Size(min=3, max=20) String username,
	@NotBlank @Size(max=50) @Email String email,
	Set<String> role,
	@NotBlank @Size(min=6, max=40) String password
){

}

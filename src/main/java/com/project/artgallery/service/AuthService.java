package com.project.artgallery.service;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.project.artgallery.payload.AuthenticationResult;
import com.project.artgallery.payload.LoginRequest;
import com.project.artgallery.payload.SignupRequest;
import com.project.artgallery.payload.UserInfoResponse;

public interface AuthService {
	public AuthenticationResult login(LoginRequest loginRequest);
	public ResponseEntity<?> register(SignupRequest signUpRequest);
	public UserInfoResponse getCurrentUserDetails(Authentication authentication);
	public ResponseCookie logoutUser();
}

package com.project.artgallery.payload;

import org.springframework.http.ResponseCookie;

public record AuthenticationResult (
		ResponseCookie jwtCookie,
		UserInfoResponse response
		){}

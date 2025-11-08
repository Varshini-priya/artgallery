package com.project.artgallery.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.artgallery.entity.AppRole;
import com.project.artgallery.entity.Users;
import com.project.artgallery.payload.AuthenticationResult;
import com.project.artgallery.payload.LoginRequest;
import com.project.artgallery.payload.MessageResponse;
import com.project.artgallery.payload.SignupRequest;
import com.project.artgallery.payload.UserInfoResponse;
import com.project.artgallery.repository.UsersRepository;
import com.project.artgallery.security.JwtUtils;
import com.project.artgallery.security.UserDetailsImpl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
 
    @Override
    public AuthenticationResult login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        
 
        UserInfoResponse response = new UserInfoResponse(userDetails.getId(),
                userDetails.getUsername(),
                jwtCookie.toString(),
                userDetails.getEmail(),
                roles);
 
        return new AuthenticationResult(jwtCookie, response);
    }
 
    @Override
    public ResponseEntity<?> register(SignupRequest signUpRequest) {
        if (usersRepository.existsByUserName(signUpRequest.username())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (usersRepository.existsByEmail(signUpRequest.email())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
 
        Users user = new Users(signUpRequest.username(), signUpRequest.email(), encoder.encode(signUpRequest.password()));
        Set<String> strRoles = signUpRequest.role();
        Set<AppRole> roles = new HashSet<>();
 
        if (strRoles == null || strRoles.isEmpty()) {
            roles.add(AppRole.ROLE_USER);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equalsIgnoreCase(role)) {
                    roles.add(AppRole.ROLE_ADMIN);
                } else {
                    roles.add(AppRole.ROLE_USER);
                }
            });
        }
        user.setRoles(roles);
        usersRepository.save(user);
        
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    
    @Override
    public UserInfoResponse getCurrentUserDetails(Authentication authentication) {
    	UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
         List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }
    
    
    @Override
    public ResponseCookie logoutUser() {
        return jwtUtils.getCleanJwtCookie();
    }
}

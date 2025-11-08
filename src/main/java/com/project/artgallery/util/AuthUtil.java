package com.project.artgallery.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.artgallery.entity.Users;
import com.project.artgallery.repository.UsersRepository;


@Component
public class AuthUtil {
	  @Autowired
	    private UsersRepository usersRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

    /**
     * Get the currently authenticated user's email (username).
     */
    public String loggedInEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("User is not authenticated");
        }

        return authentication.getName(); // usually the email or username
    }
    public Users getUserByEmailOrUsername(String identifier) {
        return usersRepository.findByEmail(identifier)
                .or(() -> usersRepository.findByUserName(identifier))
                .orElseThrow(() -> new RuntimeException("User not found: " + identifier));
    }


    // Validate encoded password
    public boolean isPasswordValid(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // Encode password (for registration)
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}


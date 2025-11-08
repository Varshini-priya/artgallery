package com.project.artgallery.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.artgallery.entity.Users;
import com.project.artgallery.repository.UsersRepository;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired 
    UsersRepository userRepository;
 
    public Users getActiveUser(String username) {
    	
    	return userRepository.findByUserName(username).orElse(null);  
    }
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Users user;
    	
    	user = getActiveUser(email);
    	if (user==null) {
    	user = userRepository.findByEmail(email)
    			.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email))
    	        ;
    	}
    	
        return UserDetailsImpl.build(user);
    }
    
    
}
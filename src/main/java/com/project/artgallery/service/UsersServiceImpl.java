package com.project.artgallery.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.artgallery.entity.AppRole;
import com.project.artgallery.entity.Users;
import com.project.artgallery.exception.UserNotFoundException;
import com.project.artgallery.repository.UsersRepository;
import com.project.artgallery.util.AuthUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService{

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired 
	AuthUtil authUtil;
	
	@Autowired
	FavoriteListService favListService;

	@Override
	public Users saveUser(Users user) {
		Users savedUser = usersRepository.save(user);
		favListService.createFavoriteList(savedUser);
		return savedUser;
	}

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public Users findUsersById(Long userId) {
		return usersRepository.findById(userId)
				.orElseThrow(()-> new UserNotFoundException("User",userId));
	}

	@Override
	public void deleteUser(Long userId) {
		usersRepository.deleteById(userId);
		System.out.println("User deleted successfully with ID: "+userId);
		
	}

	@Override
	public Users getLoggedInUser() {
		String email = authUtil.loggedInEmail();
		return usersRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("Email",email));
	}
	
	@Override
	public Users findUserByEmail(String email) {
		return usersRepository.findByEmail(email)
				.orElseThrow(()-> new UserNotFoundException("Email",email));
	}

	@Override
	public Users updateUser(Long userId, Users users) {
		Optional<Users> userToBeUpdated = usersRepository.findById(userId);
		Users user = null;
		if (userToBeUpdated.get() != null) {
			user = userToBeUpdated.get();
			user.setUserName(users.getUserName());
			user.setEmail(users.getEmail());
			user.setPassword(users.getPassword());
			user.setRoles(users.getRoles());
		}

		Users savedUsers = usersRepository.save(user);
		return savedUsers;
	}

	@Override
	public void addUserRole(Long userId, AppRole role) {
		Optional<Users> userToBeUpdated = usersRepository.findById(userId);
		Users user = null;
		Set<AppRole> roles = new HashSet<>();
		if (userToBeUpdated.get() != null) {
			user = userToBeUpdated.get();
			roles=user.getRoles();
			roles.add(role);
			user.setRoles(roles);
		}
		usersRepository.save(user);
		System.out.println("Role added : "+role+" to user : "+user.getUserName());
	}

	@Override
	public void removeUserRole(Long userId, AppRole role) {
		Optional<Users> userToBeUpdated = usersRepository.findById(userId);
		Users user = null;
		Set<AppRole> roles = new HashSet<>();
		if (userToBeUpdated.get() != null) {
			user = userToBeUpdated.get();
			roles=user.getRoles();
			roles.remove(role);
			user.setRoles(roles);
		}
		usersRepository.save(user);
		System.out.println("User role removed: "+role+" from user : "+user.getUserName());
		
	}
}

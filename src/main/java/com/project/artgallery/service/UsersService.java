package com.project.artgallery.service;

import java.util.List;

import com.project.artgallery.entity.AppRole;
import com.project.artgallery.entity.Users;


public interface UsersService {
	public Users saveUser(Users user);
	public List<Users> getAllUsers();
	public Users findUserByEmail(String email);
	public Users updateUser(Long userId, Users users);
	public void addUserRole(Long userId, AppRole role);
	public void removeUserRole(Long userId, AppRole role);
	public Users findUsersById(Long userId);
	public void deleteUser(Long userId);
	public Users getLoggedInUser();
}

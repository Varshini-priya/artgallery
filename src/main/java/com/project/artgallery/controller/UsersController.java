package com.project.artgallery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.artgallery.entity.Users;
import com.project.artgallery.payload.ArtDTO;
import com.project.artgallery.payload.ArtistDTO;
import com.project.artgallery.service.ArtService;
import com.project.artgallery.service.ArtistService;
import com.project.artgallery.service.CategoryService;
import com.project.artgallery.service.UsersService;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	ArtService artService;
	
	@Autowired
    ArtistService artistService;
	
	@Autowired
    CategoryService categoryService;
	
	@GetMapping("/getall/{status}")
	public ResponseEntity<List<ArtDTO>> getAllArtsByStatus(@PathVariable boolean status){
		List<ArtDTO> artWorks=artService.getArtByStatus(status);
		return new ResponseEntity<List<ArtDTO>>(artWorks, HttpStatus.OK);
	}
	
	@GetMapping("/get/{artId}")
	public ResponseEntity<ArtDTO> getArtById(@PathVariable Long artId){
		ArtDTO artWork=artService.getArtById(artId);
		return new ResponseEntity<ArtDTO>(artWork, HttpStatus.OK);
	}

	@PutMapping("/{userId}/update")
	public ResponseEntity<Users> updateUserById(@PathVariable Long userId, @RequestBody Users user){
		Users savedUser = usersService.updateUser(userId, user);
		return new ResponseEntity<Users>(savedUser, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
		Users user = usersService.findUsersById(userId);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@GetMapping("/artist/{artistId}")
	public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long artistId){
		ArtistDTO artist = artistService.getArtistById(artistId);
		return new ResponseEntity<ArtistDTO>(artist, HttpStatus.OK);
	}
	
	@GetMapping("/artist/{artistId}/arts")
	public ResponseEntity<List<ArtDTO>> getAllArtsByArtistId(@PathVariable Long artistId){
		List<ArtDTO> listArts = artService.getAllArtsByArtistId(artistId);
		return new ResponseEntity<List<ArtDTO>>(listArts, HttpStatus.OK);
	}
	
}

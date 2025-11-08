package com.project.artgallery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.artgallery.payload.ArtDTO;
import com.project.artgallery.service.ArtService;
import com.project.artgallery.service.ArtistService;
import com.project.artgallery.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/art")
public class ArtController {
	
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
	
	@PostMapping("/create")
	public ResponseEntity<ArtDTO> createArt(@RequestBody ArtDTO artDto){
		return new ResponseEntity<ArtDTO>(artService.createArt(artDto),HttpStatus.OK);
	}
	
	@PutMapping("/update/{artId}")
	public ResponseEntity<ArtDTO> updateArt(@PathVariable Long artId, @RequestBody ArtDTO artDto){
		return new ResponseEntity<ArtDTO>(artService.updateArt(artId, artDto),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{artId}")
	public void deleteArt(@PathVariable Long artId) {
		artService.deleteArt(artId);
		
	}
	
}

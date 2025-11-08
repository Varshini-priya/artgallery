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
import com.project.artgallery.payload.ArtistDTO;
import com.project.artgallery.service.ArtService;
import com.project.artgallery.service.ArtistService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/artist")
public class ArtistController {
	
	@Autowired
	ArtService artService;
	
	@Autowired
    ArtistService artistService;
	
	@GetMapping("/artist/{artistId}/arts")
	public ResponseEntity<List<ArtDTO>> getAllArtsByArtistId(@PathVariable Long artistId){
		List<ArtDTO> listArts = artService.getAllArtsByArtistId(artistId);
		return new ResponseEntity<List<ArtDTO>>(listArts, HttpStatus.OK);
	}
	
	@PostMapping("/artist")
	public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artist){
		return new ResponseEntity<ArtistDTO>(artistService.createArtist(artist), HttpStatus.OK);
	}
	
	@PutMapping("/update/{artistId}")
	public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long artistId, @RequestBody ArtistDTO artist){
		return new ResponseEntity<ArtistDTO>(artistService.updateArtist(artistId, artist), HttpStatus.OK);
	}
	
	@GetMapping("/artist/{artistId}")
	public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long artistId){
		ArtistDTO artist = artistService.getArtistById(artistId);
		return new ResponseEntity<ArtistDTO>(artist, HttpStatus.OK);
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

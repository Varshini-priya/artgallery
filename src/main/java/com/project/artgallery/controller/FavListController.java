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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.artgallery.payload.ArtFavDTO;
import com.project.artgallery.payload.ArtistFavDTO;
import com.project.artgallery.service.ArtFavService;
import com.project.artgallery.service.ArtistFavService;
import com.project.artgallery.service.FavoriteListService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/fav")
public class FavListController {
	@Autowired
	FavoriteListService favListService;
	
	@Autowired
	ArtFavService artFavService;
	
	@Autowired
	ArtistFavService artistFavService;
	
	@GetMapping("/artfav/{favId}")
	public ResponseEntity<List<ArtFavDTO>> getArtsByFavId(@PathVariable Long favId){
		return new ResponseEntity<List<ArtFavDTO>>(artFavService.getFavoritesByFavListId(favId),HttpStatus.OK);
	}
	
	@GetMapping("/artistfav/{favId}")
	public ResponseEntity<List<ArtistFavDTO>> getArtistByFavId(@PathVariable Long favId){
		return new ResponseEntity<List<ArtistFavDTO>>(artistFavService.getFavoritesByFavListId(favId),HttpStatus.OK);
	}
	
	@PostMapping("/artfav/{favId}/{artId}")
	public ResponseEntity<ArtFavDTO> createArtFav(@PathVariable Long favId, @PathVariable Long artId){
		return new ResponseEntity<ArtFavDTO>(artFavService.addArtFavorite(favId, artId),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/artFav/{artFavId}")
	public void deleteArtFav(@PathVariable Long artFavId) {
		artFavService.removeArtFavorite(artFavId);
	}
	
	@PostMapping("/artistfav/{favId}/{artistId}")
	public ResponseEntity<ArtistFavDTO> createArtistFav(@PathVariable Long favId, @PathVariable Long artistId){
		return new ResponseEntity<ArtistFavDTO>(artistFavService.addArtistFavorite(favId, artistId),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/artistFav/{artistFavId}")
	public void deleteArtistFav(@PathVariable Long artistFavId) {
		artistFavService.removeArtistFavorite(artistFavId);
	}
	
}

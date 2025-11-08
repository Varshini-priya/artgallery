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

import com.project.artgallery.entity.Users;
import com.project.artgallery.payload.ArtDTO;
import com.project.artgallery.payload.ArtistDTO;
import com.project.artgallery.payload.CategoryDTO;
import com.project.artgallery.service.ArtFavService;
import com.project.artgallery.service.ArtService;
import com.project.artgallery.service.ArtistFavService;
import com.project.artgallery.service.ArtistService;
import com.project.artgallery.service.CategoryService;
import com.project.artgallery.service.UsersService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	ArtService artService;
	
	@Autowired
    ArtistService artistService;
	
	@Autowired
    CategoryService categoryService;
	
	@Autowired
    ArtFavService artFavService;
	
	@Autowired
    ArtistFavService artistFavService;
	
	@GetMapping("/user")
	public ResponseEntity<List<Users>> getAllUserss() {
		List<Users> usersList = usersService.getAllUsers();
		return new ResponseEntity<List<Users>>(usersList, HttpStatus.OK);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUsers(@PathVariable Long userId) {
	 usersService.deleteUser(userId);
		return new ResponseEntity<>("User with ID : " + userId +" Deleted Successfully!!! ", HttpStatus.OK);
	}
	
	@GetMapping("/artists")
	public ResponseEntity<List<ArtistDTO>> getAllArtists(){
		return new ResponseEntity<List<ArtistDTO>>(artistService.getAllArtists(),HttpStatus.OK);
	}
	
	@GetMapping("/art/submission")
	public ResponseEntity<List<ArtDTO>> getArtSubmissions(){
		return new ResponseEntity<List<ArtDTO>>(artService.getArtByStatus(false),HttpStatus.OK);
	}
	
	@PutMapping("/art/approveorreject/{artId}/{status}")
	public ResponseEntity<ArtDTO> updateArtStatus(@PathVariable Long artId, @PathVariable boolean status){
		return new ResponseEntity<ArtDTO>(artService.updateArtStatusById(artId, status), HttpStatus.OK);
	}
	
	@DeleteMapping("/art/delete/{artId}")
	public ResponseEntity<String> deleteArtById(@PathVariable Long artId){
		artService.deleteArt(artId);
		return new ResponseEntity<String>("Art Deleted", HttpStatus.OK);
	}
	
	@PostMapping("/categories")
    public ResponseEntity<CategoryDTO> adminCreateCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO created = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> adminUpdateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updated = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> adminDeleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

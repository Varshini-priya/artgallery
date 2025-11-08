package com.project.artgallery.service;

import java.util.List;

import com.project.artgallery.payload.ArtDTO;

public interface ArtService {
	ArtDTO createArt(ArtDTO artDTO);
	List<ArtDTO> getAllArts();
	ArtDTO getArtById(Long artId);
	List<ArtDTO> getAllArtsByArtistId(Long artistId);
	List<ArtDTO> getAllArtsByCategoryId(Long categoryId);
	ArtDTO updateArt(Long artId, ArtDTO artDTO);
	void deleteArt(Long artId);
	List<ArtDTO> getArtByStatus(boolean status);
	ArtDTO updateArtStatusById(Long artId, boolean status);
}	

package com.project.artgallery.service;

import java.util.List;

import com.project.artgallery.payload.ArtistDTO;

public interface ArtistService {
	ArtistDTO createArtist(ArtistDTO artistDTO);
	List<ArtistDTO> getAllArtists();
	ArtistDTO getArtistById(Long id);
	ArtistDTO updateArtist(Long id, ArtistDTO artistDTO);
	void deleteArtist(Long id);
}

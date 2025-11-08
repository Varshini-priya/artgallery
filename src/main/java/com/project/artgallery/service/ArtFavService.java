package com.project.artgallery.service;

import java.util.List;

import com.project.artgallery.payload.ArtDTO;
import com.project.artgallery.payload.ArtFavDTO;

public interface ArtFavService {
	ArtFavDTO addArtFavorite(Long favId, Long artId);
	void removeArtFavorite(Long id);
	List<ArtFavDTO> getAllArtFavorites();
	ArtFavDTO getArtFavById(Long ArtFavId);
	List<ArtFavDTO> getFavoritesByFavListId(Long favListId);
	List<ArtDTO> getArtByFavListId(Long favListId);
}

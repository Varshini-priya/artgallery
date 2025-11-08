package com.project.artgallery.service;

import java.util.List;

import com.project.artgallery.payload.ArtistDTO;
import com.project.artgallery.payload.ArtistFavDTO;

public interface ArtistFavService {
	ArtistFavDTO addArtistFavorite(Long favId, Long artistId);
	void removeArtistFavorite(Long id);
	List<ArtistFavDTO> getAllArtistFavorites();
	ArtistFavDTO getArtistFavById(Long artistFavId);
	List<ArtistFavDTO> getFavoritesByFavListId(Long favListId);
	List<ArtistDTO> getArtistsByFavListId(Long favListId);
}

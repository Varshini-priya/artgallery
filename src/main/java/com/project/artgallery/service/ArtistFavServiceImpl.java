package com.project.artgallery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.artgallery.entity.Artist;
import com.project.artgallery.entity.ArtistFav;
import com.project.artgallery.entity.FavoriteList;
import com.project.artgallery.exception.ResourceNotFoundException;
import com.project.artgallery.payload.ArtistDTO;
import com.project.artgallery.payload.ArtistFavDTO;
import com.project.artgallery.repository.ArtistFavRepository;
import com.project.artgallery.repository.ArtistRepository;
import com.project.artgallery.repository.FavoriteListRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArtistFavServiceImpl implements ArtistFavService{
		
	private static final Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ArtistFavRepository artistFavRepository;
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	FavoriteListRepository favListRepository;

	@Override
	public ArtistFavDTO addArtistFavorite(Long favId, Long artistId) {
		FavoriteList favList= favListRepository.findById(favId).orElseThrow(
				()-> new ResourceNotFoundException("FavList not found"));
		Artist artist = artistRepository.findById(artistId).orElseThrow(
				()-> new ResourceNotFoundException("Artist not found"));
		ArtistFav artistFav = new ArtistFav();
		artistFav.setFavList(favList);
		artistFav.setArtist(artist);
		return modelMapper.map(artistFavRepository.save(artistFav), ArtistFavDTO.class);
	}

	@Override
	public void removeArtistFavorite(Long id) {
		artistFavRepository.deleteById(id);
		logger.debug("artistFav is deleted by id: "+id);
		
	}

	@Override
	public List<ArtistFavDTO> getAllArtistFavorites() {
		return artistFavRepository.findAll().stream().map((fav)-> modelMapper.map(fav, ArtistFavDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ArtistFavDTO getArtistFavById(Long artistFavId) {
		return modelMapper.map(artistFavRepository.findById(artistFavId), ArtistFavDTO.class);
	}

	@Override
	public List<ArtistFavDTO> getFavoritesByFavListId(Long favListId) {
		return artistFavRepository.findByFavId(favListId).stream().map((fav)->modelMapper.map(fav, ArtistFavDTO.class)).collect(Collectors.toList());
	}
	
	@Override
	public List<ArtistDTO> getArtistsByFavListId(Long favListId) {
		List<ArtistDTO> artists = artistFavRepository.findByFavId(favListId)
				.stream().map(
						(fav)->
							modelMapper.map(artistRepository.findById(fav.getArtist().getArtistId()).orElseGet(null),ArtistDTO.class)
						).collect(Collectors.toList());
		return artists;
	}

}

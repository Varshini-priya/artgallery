package com.project.artgallery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.artgallery.entity.Art;
import com.project.artgallery.entity.ArtFav;
import com.project.artgallery.entity.FavoriteList;
import com.project.artgallery.exception.ResourceNotFoundException;
import com.project.artgallery.payload.ArtDTO;
import com.project.artgallery.payload.ArtFavDTO;
import com.project.artgallery.repository.ArtFavRepository;
import com.project.artgallery.repository.ArtRepository;
import com.project.artgallery.repository.FavoriteListRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArtFavServiceImpl implements ArtFavService{
	
	private static final Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ArtFavRepository artFavRepository;
	
	@Autowired
	ArtRepository artRepository;
	
	@Autowired
	FavoriteListRepository favListRepository;
	
	@Override
	public ArtFavDTO addArtFavorite(Long favId, Long artId) {
		FavoriteList favList= favListRepository.findById(favId).orElseThrow(
				()-> new ResourceNotFoundException("FavList not found"));
		Art art = artRepository.findById(artId).orElseThrow(
				()-> new ResourceNotFoundException("Art not found"));
		ArtFav artFav = new ArtFav();
		artFav.setFavList(favList);
		artFav.setArt(art);
		return modelMapper.map(artFavRepository.save(artFav), ArtFavDTO.class);
	}

	@Override
	public void removeArtFavorite(Long id) {
		artFavRepository.deleteById(id);
		logger.debug("artFav is deleted by id: "+id);
	}

	@Override
	public List<ArtFavDTO> getAllArtFavorites() {
		return artFavRepository.findAll().stream().map((fav)-> modelMapper.map(fav, ArtFavDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ArtFavDTO getArtFavById(Long ArtFavId) {
		return modelMapper.map(artFavRepository.findById(ArtFavId), ArtFavDTO.class);
	}

	@Override
	public List<ArtFavDTO> getFavoritesByFavListId(Long favListId) {
		return artFavRepository.findByFavId(favListId).stream().map((fav)->modelMapper.map(fav, ArtFavDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<ArtDTO> getArtByFavListId(Long favListId) {
		List<ArtDTO> arts = artFavRepository.findByFavId(favListId)
				.stream().map(
						(fav)->
							modelMapper.map(artRepository.findById(fav.getArt().getArtId()).orElseGet(null),ArtDTO.class)
						).collect(Collectors.toList());
		return arts;
	}

}

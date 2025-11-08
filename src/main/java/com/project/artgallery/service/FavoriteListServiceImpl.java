package com.project.artgallery.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.artgallery.entity.FavoriteList;
import com.project.artgallery.entity.Users;
import com.project.artgallery.payload.FavoriteListDTO;
import com.project.artgallery.repository.FavoriteListRepository;

@Service
@Transactional
public class FavoriteListServiceImpl implements FavoriteListService{

	private static final Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	FavoriteListRepository favListRepository;
	
	@Autowired
	ArtFavService artFavService;
	
	@Autowired
	ArtistFavService artistFavService;
	
	@Override
	public FavoriteListDTO getFavoriteListByUserId(Long userId) {
		FavoriteList favList = favListRepository.findByUserId(userId);
		return modelMapper.map(favList, FavoriteListDTO.class);
	}

	@Override
	public FavoriteListDTO createFavoriteList(Users user) {
		FavoriteList favList = new FavoriteList();
		favList.setUser(user);
		FavoriteList saved = favListRepository.save(favList);
//		artFavService.addArtFavorite(saved);
//		artistFavService.addArtistFavorite(saved);
		return modelMapper.map(saved, FavoriteListDTO.class);
	}

	@Override
	public void deleteFavoriteList(Long id) {
		favListRepository.deleteById(id);
		logger.debug("Deleted the favList with id: "+id);
	}

}

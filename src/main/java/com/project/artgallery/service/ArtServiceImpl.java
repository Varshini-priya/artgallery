package com.project.artgallery.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.artgallery.entity.Art;
import com.project.artgallery.entity.Artist;
import com.project.artgallery.entity.Users;
import com.project.artgallery.exception.ResourceNotFoundException;
import com.project.artgallery.payload.ArtDTO;
import com.project.artgallery.repository.ArtRepository;
import com.project.artgallery.repository.ArtistRepository;
import com.project.artgallery.repository.CategoryRepository;
import com.project.artgallery.repository.UsersRepository;
import com.project.artgallery.util.AuthUtil;

@Service
@Transactional
public class ArtServiceImpl implements ArtService{
	
	private static final Logger logger = LoggerFactory.getLogger(ArtServiceImpl.class);
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ArtRepository artRepository;
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	AuthUtil authUtil;
	
	@Autowired
	UsersRepository userRepository;
	
	@Override
	public ArtDTO createArt(ArtDTO artDTO) {
		Users user = userRepository.findUsersByEmail(authUtil.loggedInEmail());
		Artist artist = artistRepository.findByUserId(user.getUserId());
		Art art = modelMapper.map(artDTO, Art.class);
		art.setArtist(artist);
		art.setCreatedDate(LocalDateTime.now());
		art.setStatus(false);
		return modelMapper.map(artRepository.save(art), ArtDTO.class);
	}

	@Override
	public List<ArtDTO> getAllArts() {
		return artRepository.findAll().stream().map((art)->modelMapper.map(art, ArtDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ArtDTO getArtById(Long artId) {
		return modelMapper.map(artRepository.findById(artId),ArtDTO.class);
	}

	@Override
	public List<ArtDTO> getAllArtsByArtistId(Long artistId) {
		return artRepository.findByArtistId(artistId).stream().map((art)->modelMapper.map(art, ArtDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<ArtDTO> getAllArtsByCategoryId(Long categoryId) {
		return artRepository.findByCategoryId(categoryId).stream().map((art)->modelMapper.map(art, ArtDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ArtDTO updateArt(Long artId, ArtDTO artDTO) {
		Art art = artRepository.findById(artId)
				.orElseThrow(()->new ResourceNotFoundException("Error", "Art not found for the id", artId));
		art.setArtName(artDTO.getArtName());
		art.setCategory(artDTO.getCategory());
		art.setDescription(artDTO.getDescription());
		art.setImage(artDTO.getImage());
		art.setPrice(artDTO.getPrice());
		return modelMapper.map(artRepository.save(art), ArtDTO.class);
	}

	@Override
	public void deleteArt(Long artId) {
		artRepository.deleteById(artId);
		logger.debug("Art Deleted with Id: "+artId);
		
	}

	@Override
	public List<ArtDTO> getArtByStatus(boolean status) {
		return artRepository.findByStatus(status).stream().map((art)->modelMapper.map(art, ArtDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ArtDTO updateArtStatusById(Long artId, boolean status) {
		Art art = artRepository.findById(artId)
				.orElseThrow(()->new ResourceNotFoundException("Error", "Art not found for the id", artId));
		art.setStatus(status);
		return modelMapper.map(artRepository.save(art), ArtDTO.class);
	}

}

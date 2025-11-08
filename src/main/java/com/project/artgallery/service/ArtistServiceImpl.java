package com.project.artgallery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.artgallery.entity.Artist;
import com.project.artgallery.entity.Users;
import com.project.artgallery.exception.ResourceNotFoundException;
import com.project.artgallery.payload.ArtistDTO;
import com.project.artgallery.repository.ArtRepository;
import com.project.artgallery.repository.ArtistRepository;
import com.project.artgallery.repository.UsersRepository;
import com.project.artgallery.util.AuthUtil;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService{
	
	private static final Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ArtRepository artRepository;
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	AuthUtil authUtil;
	
	@Autowired
	UsersRepository userRepository;

	@Override
	public ArtistDTO createArtist(ArtistDTO artistDTO) {
		Artist artist = modelMapper.map(artistDTO, Artist.class);
		Users user = userRepository.findUsersByEmail(authUtil.loggedInEmail());
		artist.setUser(user);
		return modelMapper.map(artistRepository.save(artist), ArtistDTO.class);
	}

	@Override
	public List<ArtistDTO> getAllArtists() {
		return artistRepository.findAll().stream().map((artist)->modelMapper.map(artist, ArtistDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ArtistDTO getArtistById(Long id) {
		return modelMapper.map(artistRepository.findById(id), ArtistDTO.class);
	}

	@Override
	public ArtistDTO updateArtist(Long id, ArtistDTO artistDTO) {
		Artist artist = artistRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Artist not found for the id: "+id) );
		artist.setArtistName(artistDTO.getArtistName());
		artist.setProfilePicture(artistDTO.getProfilePicture());
		artist.setAbout(artistDTO.getAbout());
		return modelMapper.map(artistRepository.save(artist), ArtistDTO.class);
	}

	@Override
	public void deleteArtist(Long id) {
		artistRepository.deleteById(id);
		logger.debug("Artist deleted with Id: "+id);
	}

}

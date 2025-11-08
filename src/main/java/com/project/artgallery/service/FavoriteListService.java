package com.project.artgallery.service;

import com.project.artgallery.entity.Users;
import com.project.artgallery.payload.FavoriteListDTO;

public interface FavoriteListService {
	FavoriteListDTO getFavoriteListByUserId(Long userId);
    FavoriteListDTO createFavoriteList(Users user);
    void deleteFavoriteList(Long id);
}

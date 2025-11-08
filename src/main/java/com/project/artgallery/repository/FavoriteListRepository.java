package com.project.artgallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.artgallery.entity.FavoriteList;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, Long>{
	@Query(value="select f from favorite_list f where f.user_id =?1", nativeQuery = true)
	FavoriteList findByUserId(Long userId);
}

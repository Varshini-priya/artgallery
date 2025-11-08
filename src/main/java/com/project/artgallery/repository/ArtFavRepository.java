package com.project.artgallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.artgallery.entity.ArtFav;

@Repository
public interface ArtFavRepository extends JpaRepository<ArtFav, Long> {
	@Query(value="select a from art_fav a where a.fav_id =?1", nativeQuery = true)
	List<ArtFav> findByFavId(Long favId);
}

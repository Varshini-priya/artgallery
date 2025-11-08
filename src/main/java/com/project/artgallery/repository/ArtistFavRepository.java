package com.project.artgallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.artgallery.entity.ArtistFav;

@Repository
public interface ArtistFavRepository extends JpaRepository<ArtistFav, Long> {
	@Query(value="select a from artist_fav a where a.fav_id =?1", nativeQuery = true)
	List<ArtistFav> findByFavId(Long favId);
}

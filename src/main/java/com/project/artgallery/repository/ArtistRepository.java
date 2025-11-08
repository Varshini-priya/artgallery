package com.project.artgallery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.artgallery.entity.Artist;


@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
	
	@Query(value="select a from artist a where a.user_id =?1", nativeQuery = true)
	Artist findByUserId(Long userId);
}

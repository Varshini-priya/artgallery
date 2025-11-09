package com.project.artgallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.artgallery.entity.Art;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {
	@Query(value="select a from art a where a.artist_id =?1", nativeQuery = true)
	List<Art> findByArtistId(Long artistId);
	
	@Query(value="select a from art a where a.category_id =?1", nativeQuery = true)
	List<Art> findByCategoryId(Long categoryId);
	
	@Modifying
	@Query(value="update art a set a.status=?2 where a.art_id =?1", nativeQuery = true)
	void updateStatusByArt(Long artId, boolean status);
	
	@Query(value="select a from art a where a.status =?1", nativeQuery = true)
	List<Art> findByStatus(boolean status);
}

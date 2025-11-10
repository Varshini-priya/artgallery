package com.project.artgallery.payload;

import java.util.List;

public class ArtistProfileDTO {
    private Long artistId;
    private String artistName;
    private String about;
    private String profilePicture;
    private UserInfoResponse user;
    private List<ArtDTO> artList;
    private int totalArtworks;
    private int approvedArtworks;
    private int pendingArtworks;

    public ArtistProfileDTO() {
    }

    public ArtistProfileDTO(Long artistId, String artistName, String about, String profilePicture, 
                           UserInfoResponse user, List<ArtDTO> artList) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.about = about;
        this.profilePicture = profilePicture;
        this.user = user;
        this.artList = artList;
        calculateStats();
    }

    private void calculateStats() {
        this.totalArtworks = artList != null ? artList.size() : 0;
        this.approvedArtworks = artList != null ? (int) artList.stream()
                .filter(art -> art.isStatus()).count() : 0;
        this.pendingArtworks = artList != null ? (int) artList.stream()
                .filter(art -> !art.isStatus()).count() : 0;
    }

    // Getters and Setters
    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public UserInfoResponse getUser() {
        return user;
    }

    public void setUser(UserInfoResponse user) {
        this.user = user;
    }

    public List<ArtDTO> getArtList() {
        return artList;
    }

    public void setArtList(List<ArtDTO> artList) {
        this.artList = artList;
        calculateStats();
    }

    public int getTotalArtworks() {
        return totalArtworks;
    }

    public void setTotalArtworks(int totalArtworks) {
        this.totalArtworks = totalArtworks;
    }

    public int getApprovedArtworks() {
        return approvedArtworks;
    }

    public void setApprovedArtworks(int approvedArtworks) {
        this.approvedArtworks = approvedArtworks;
    }

    public int getPendingArtworks() {
        return pendingArtworks;
    }

    public void setPendingArtworks(int pendingArtworks) {
        this.pendingArtworks = pendingArtworks;
    }
}
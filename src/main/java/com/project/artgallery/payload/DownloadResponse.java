package com.project.artgallery.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DownloadResponse {
    private String fileName;
    private String fileUrl;
    private Long fileSize;
    private String message;
    private boolean success;
    private String artId;
    private LocalDateTime uploadTime;
    private String fileType;

    // Constructors, Getters and Setters
    public DownloadResponse() {}

    public DownloadResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.uploadTime = LocalDateTime.now();
    }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getArtId() { return artId; }
    public void setArtId(String artId) { this.artId = artId; }
    public LocalDateTime getUploadTime() { return uploadTime; }
    public void setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
}
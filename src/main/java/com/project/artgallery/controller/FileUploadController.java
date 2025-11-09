package com.project.artgallery.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.artgallery.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {

    private final FileService fileService;

    @Value("${project.image}")
    private String path;

    // Allowed file types
    private final List<String> allowedContentTypes = Arrays.asList(
        "image/jpeg", "image/png", "image/gif", "image/webp"
    );

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile image) {
        try {
            // Validate file type
            if (!allowedContentTypes.contains(image.getContentType())) {
                return ResponseEntity.badRequest().body("Only image files are allowed");
            }

            // Validate file size (10MB)
            if (image.getSize() > 10 * 1024 * 1024) {
                return ResponseEntity.badRequest().body("File size must be less than 10MB");
            }

            String fileName = fileService.uploadImage(path, image);
            return ResponseEntity.ok(fileName);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping(value = "/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        try {
            InputStream resource = fileService.getResource(path, imageName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(resource, response.getOutputStream());
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getWriter().write("Image not found: " + imageName);
        }
    }
}
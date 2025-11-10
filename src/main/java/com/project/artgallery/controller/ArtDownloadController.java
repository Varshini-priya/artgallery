package com.project.artgallery.controller;

import com.project.artgallery.payload.PaymentResponse;
import com.project.artgallery.service.ArtDownloadService;
import com.project.artgallery.service.ArtService;
import com.project.artgallery.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/art-download")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ArtDownloadController {

    @Autowired
    private ArtDownloadService artDownloadService;

    @Autowired
    private ArtService artService;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/purchase/{artId}")
    public ResponseEntity<PaymentResponse> purchaseArtwork(
            @PathVariable Long artId,
            @RequestParam BigDecimal amount) {
        try {
            // Get current user ID from authentication
            Long userId = authUtil.getUserByEmailOrUsername(authUtil.loggedInEmail()).getUserId();
            
            PaymentResponse response = artDownloadService.purchaseArtwork(artId, userId, amount);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            PaymentResponse errorResponse = new PaymentResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage("Purchase failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/download/{artId}/{fileName}")
    public ResponseEntity<byte[]> downloadArtwork(
            @PathVariable Long artId,
            @PathVariable String fileName,
            @RequestParam String token) {
        try {
            byte[] fileContent = artDownloadService.downloadArtwork(artId, fileName, token);
            
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, 
                           "attachment; filename=\"" + fileName + "\"")
                    .body(fileContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/upload/{artId}")
    public ResponseEntity<?> uploadArtworkFile(
            @PathVariable Long artId,
            @RequestParam("file") MultipartFile file) {
        try {
            var response = artDownloadService.uploadArtworkFile(file, artId);
            if (response.isSuccess()) {
                // Update art record with file information if needed
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/payment/status/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentStatus(@PathVariable String paymentId) {
        PaymentResponse response = artDownloadService.getPaymentStatus(paymentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/payment/history")
    public ResponseEntity<List<PaymentResponse>> getUserPaymentHistory() {
        try {
            Long userId = authUtil.getUserByEmailOrUsername(authUtil.loggedInEmail()).getUserId();
            List<PaymentResponse> history = artDownloadService.getUserPaymentHistory(userId);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/payment/refund/{paymentId}")
    public ResponseEntity<PaymentResponse> refundPayment(@PathVariable String paymentId) {
        PaymentResponse response = artDownloadService.refundPayment(paymentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> checkDownloadServiceHealth() {
        String healthStatus = artDownloadService.checkDownloadServiceHealth();
        return ResponseEntity.ok(healthStatus);
    }
}
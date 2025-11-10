package com.project.artgallery.service;

import com.project.artgallery.client.DownloadServiceClient;
import com.project.artgallery.payload.DownloadResponse;
import com.project.artgallery.payload.PaymentRequest;
import com.project.artgallery.payload.PaymentResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ArtDownloadService {

    private static final Logger logger = LoggerFactory.getLogger(ArtDownloadService.class);

    @Autowired
    private DownloadServiceClient downloadServiceClient;

    public PaymentResponse purchaseArtwork(Long artId, Long userId, BigDecimal price) {
        try {
            logger.info("Processing payment for artId: {}, userId: {}, amount: {}", artId, userId, price);
            
            PaymentRequest paymentRequest = new PaymentRequest(
                artId.toString(), 
                userId, 
                price
            );
            
            PaymentResponse response = downloadServiceClient.processPayment(paymentRequest);
            logger.info("Payment processed: {}", response.getStatus());
            
            return response;
            
        } catch (Exception e) {
            logger.error("Error processing payment for artId: {}", artId, e);
            PaymentResponse errorResponse = new PaymentResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage("Payment processing failed: " + e.getMessage());
            return errorResponse;
        }
    }

    public byte[] downloadArtwork(Long artId, String fileName, String token) {
        try {
            logger.info("Downloading artwork: artId={}, fileName={}", artId, fileName);
            
            ResponseEntity<byte[]> response = downloadServiceClient.downloadFile(
                artId.toString(), 
                fileName, 
                token
            );
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                logger.info("Artwork downloaded successfully: {}", fileName);
                return response.getBody();
            } else {
                logger.error("Failed to download artwork: {}", fileName);
                throw new RuntimeException("Failed to download artwork");
            }
            
        } catch (Exception e) {
            logger.error("Error downloading artwork: artId={}", artId, e);
            throw new RuntimeException("Download failed: " + e.getMessage());
        }
    }

    public DownloadResponse uploadArtworkFile(MultipartFile file, Long artId) {
        try {
            logger.info("Uploading artwork file for artId: {}", artId);
            return downloadServiceClient.uploadFile(file, artId.toString());
        } catch (Exception e) {
            logger.error("Error uploading file for artId: {}", artId, e);
            DownloadResponse errorResponse = new DownloadResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("File upload failed: " + e.getMessage());
            return errorResponse;
        }
    }

    public PaymentResponse getPaymentStatus(String paymentId) {
        try {
            return downloadServiceClient.getPaymentStatus(paymentId);
        } catch (Exception e) {
            logger.error("Error getting payment status: {}", paymentId, e);
            PaymentResponse errorResponse = new PaymentResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage("Failed to get payment status: " + e.getMessage());
            return errorResponse;
        }
    }

    public List<PaymentResponse> getUserPaymentHistory(Long userId) {
        try {
            return downloadServiceClient.getUserPaymentHistory(userId);
        } catch (Exception e) {
            logger.error("Error getting payment history for user: {}", userId, e);
            throw new RuntimeException("Failed to get payment history: " + e.getMessage());
        }
    }

    public PaymentResponse refundPayment(String paymentId) {
        try {
            return downloadServiceClient.refundPayment(paymentId);
        } catch (Exception e) {
            logger.error("Error refunding payment: {}", paymentId, e);
            PaymentResponse errorResponse = new PaymentResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage("Refund failed: " + e.getMessage());
            return errorResponse;
        }
    }

    public String checkDownloadServiceHealth() {
        try {
            return downloadServiceClient.healthCheck();
        } catch (Exception e) {
            logger.error("Download service health check failed", e);
            return "Download service is unavailable";
        }
    }
}
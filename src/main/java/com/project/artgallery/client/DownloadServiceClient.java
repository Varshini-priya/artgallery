package com.project.artgallery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.artgallery.payload.DownloadResponse;
import com.project.artgallery.payload.PaymentRequest;
import com.project.artgallery.payload.PaymentResponse;

import java.util.List;

@FeignClient(name = "download-service", path = "/api/download")
public interface DownloadServiceClient {

    // Payment endpoints
    @PostMapping("/payment")
    PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/payment/status/{paymentId}")
    PaymentResponse getPaymentStatus(@PathVariable String paymentId);

    @GetMapping("/payment/history/{userId}")
    List<PaymentResponse> getUserPaymentHistory(@PathVariable Long userId);

    @PostMapping("/payment/refund/{paymentId}")
    PaymentResponse refundPayment(@PathVariable String paymentId);

    // Download endpoints
    @GetMapping("/file/{artId}/{fileName}")
    ResponseEntity<byte[]> downloadFile(
            @PathVariable("artId") String artId,
            @PathVariable("fileName") String fileName,
            @RequestParam("token") String token);

    // File upload endpoints
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    DownloadResponse uploadFile(
            @RequestPart("file") MultipartFile file,
            @RequestParam("artId") String artId);

    @GetMapping("/file/info/{fileName}")
    DownloadResponse getFileInfo(@PathVariable String fileName);

    @DeleteMapping("/file/{fileName}")
    DownloadResponse deleteFile(@PathVariable String fileName);

    // Health check
    @GetMapping("/health")
    String healthCheck();
}

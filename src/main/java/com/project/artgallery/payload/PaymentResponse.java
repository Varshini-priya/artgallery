package com.project.artgallery.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
    private String paymentId;
    private String artId;
    private Long userId;
    private BigDecimal amount;
    private String status;
    private String message;
    private LocalDateTime paymentDate;
    private String downloadToken;
    private String transactionId;
    private String paymentMethod;
    private String failureReason;

    // Constructors, Getters and Setters
    public PaymentResponse() {}

    public PaymentResponse(String paymentId, String artId, Long userId, 
                         BigDecimal amount, String status, String message) {
        this.paymentId = paymentId;
        this.artId = artId;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
        this.message = message;
        this.paymentDate = LocalDateTime.now();
    }

    // Getters and Setters for all fields
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getArtId() { return artId; }
    public void setArtId(String artId) { this.artId = artId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public String getDownloadToken() { return downloadToken; }
    public void setDownloadToken(String downloadToken) { this.downloadToken = downloadToken; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getFailureReason() { return failureReason; }
    public void setFailureReason(String failureReason) { this.failureReason = failureReason; }
}
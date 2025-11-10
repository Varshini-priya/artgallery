package com.project.artgallery.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRequest {
    private String artId;
    private Long userId;
    private BigDecimal amount;
    private String currency = "INR";
    private String paymentMethod = "CARD";

    // Constructors, Getters and Setters
    public PaymentRequest() {}

    public PaymentRequest(String artId, Long userId, BigDecimal amount) {
        this.artId = artId;
        this.userId = userId;
        this.amount = amount;
    }

    public String getArtId() { return artId; }
    public void setArtId(String artId) { this.artId = artId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
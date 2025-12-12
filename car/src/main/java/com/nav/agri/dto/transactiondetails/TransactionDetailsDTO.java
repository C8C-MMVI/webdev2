package com.nav.agri.dto.transactiondetails;

public class TransactionDetailsDTO {
    private Long transactionDetailsId;
    private Integer quantity;
    private Double basePrice;
    private Double listPrice;
    private Long productId;
    private Long transactionId;

    public Long getTransactionDetailsId() {
        return transactionDetailsId;
    }

    public void setTransactionDetailsId(Long transactionDetailsId) {
        this.transactionDetailsId = transactionDetailsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    // Getters & setters...
}

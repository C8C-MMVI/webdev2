package com.nav.agri.dto.stock;

import java.time.LocalDate;

public class StockRecordDTO {
    private Long stockRecordId;
    private Integer quantity;
    private Double unitPrice;
    private LocalDate lastUpdated;
    private Long productId;
    private Long supplierId;

    public Long getStockRecordId() {
        return stockRecordId;
    }

    public void setStockRecordId(Long stockRecordId) {
        this.stockRecordId = stockRecordId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    // Getters & setters...
}

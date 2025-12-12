package com.nav.agri.dto.stock;

import java.time.LocalDate;

public class StockRecordDTO {
    private int stockRecordId;
    private Integer quantity;
    private Double unitPrice;
    private LocalDate lastUpdated;
    private int productId;
    private int supplierId;

    public StockRecordDTO() {}

    public StockRecordDTO(int stockRecordId, Integer quantity, Double unitPrice, LocalDate lastUpdated, int productId, int supplierId) {
        this.stockRecordId = stockRecordId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lastUpdated = lastUpdated;
        this.productId = productId;
        this.supplierId = supplierId;
    }

    public int getStockRecordId() { return stockRecordId; }
    public void setStockRecordId(int stockRecordId) { this.stockRecordId = stockRecordId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }
}

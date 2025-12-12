package com.nav.agri.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    private String description;

    @Column(nullable = false)
    private Double basePrice;

    @Column(nullable = false)
    private Double listPrice;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<StockRecord> stockRecords;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<TransactionDetails> transactionDetails;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }
    public Double getListPrice() { return listPrice; }
    public void setListPrice(Double listPrice) { this.listPrice = listPrice; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public List<StockRecord> getStockRecords() { return stockRecords; }
    public void setStockRecords(List<StockRecord> stockRecords) { this.stockRecords = stockRecords; }
    public List<TransactionDetails> getTransactionDetails() { return transactionDetails; }
    public void setTransactionDetails(List<TransactionDetails> transactionDetails) { this.transactionDetails = transactionDetails; }
}

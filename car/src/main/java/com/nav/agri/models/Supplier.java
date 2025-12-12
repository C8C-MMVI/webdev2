package com.nav.agri.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer contactInfo;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<StockRecord> stockRecords;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getContactInfo() { return contactInfo; }
    public void setContactInfo(Integer contactInfo) { this.contactInfo = contactInfo; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public List<StockRecord> getStockRecords() { return stockRecords; }
    public void setStockRecords(List<StockRecord> stockRecords) { this.stockRecords = stockRecords; }
}

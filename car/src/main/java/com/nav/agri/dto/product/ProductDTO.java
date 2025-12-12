package com.nav.agri.dto.product;

public class ProductDTO {
    private int productId;
    private String productName;
    private String description;
    private Double basePrice;
    private Double listPrice;
    private int categoryId;

    public ProductDTO() {}

    public ProductDTO(int productId, String productName, String description, Double basePrice, Double listPrice, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.basePrice = basePrice;
        this.listPrice = listPrice;
        this.categoryId = categoryId;
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }

    public Double getListPrice() { return listPrice; }
    public void setListPrice(Double listPrice) { this.listPrice = listPrice; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}

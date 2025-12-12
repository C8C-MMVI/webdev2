package com.nav.agri.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductCreateDTO {
    @NotBlank(message = "Product name required")
    private String productName;
    private String description;
    @Min(1)
    private Double basePrice;
    @Min(1)
    private Double listPrice;
    private int categoryId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

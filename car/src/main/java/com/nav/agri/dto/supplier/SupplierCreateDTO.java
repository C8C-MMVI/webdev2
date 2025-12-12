package com.nav.agri.dto.supplier;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class SupplierCreateDTO {
    @NotBlank(message = "Supplier name is required")
    private String name;
    @NotBlank(message = "Contact info is required")
    @Max(11)
    private Integer contactInfo;
    @NotBlank(message = "Supplier address is required")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(Integer contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

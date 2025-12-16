package com.nav.agri.dto.transaction;

import java.time.LocalDate;
import java.util.List;

public class TransactionRequestDTO {

    private LocalDate transactionDate;
    private Double totalAmount;
    private int userId; // who made the transaction
    private List<TransactionItemDTO> items; // products + quantity + prices

    // Getters and Setters
    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public List<TransactionItemDTO> getItems() { return items; }
    public void setItems(List<TransactionItemDTO> items) { this.items = items; }
}

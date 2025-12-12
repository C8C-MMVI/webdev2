package com.nav.agri.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private LocalDate transactionDate;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionDetails> transactionDetails;

    // Getters and Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int id) { this.transactionId = id; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<TransactionDetails> getTransactionDetails() { return transactionDetails; }
    public void setTransactionDetails(List<TransactionDetails> transactionDetails) { this.transactionDetails = transactionDetails; }
}

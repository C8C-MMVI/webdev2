package com.nav.agri.service.transaction;

import com.nav.agri.dto.transaction.TransactionCreateDTO;
import com.nav.agri.dto.transaction.TransactionDTO;
import com.nav.agri.models.Transaction;
import com.nav.agri.models.User;
import com.nav.agri.repositories.TransactionRepository;
import com.nav.agri.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repo;
    private final UserRepository userRepo;

    public TransactionServiceImpl(TransactionRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public TransactionDTO createTransaction(TransactionCreateDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(dto.getTransactionDate());
        transaction.setTotalAmount(dto.getTotalAmount());
        transaction.setUser(user);

        return toDTO(repo.save(transaction));
    }

    @Override
    public TransactionDTO getTransaction(int transactionId) {
        return toDTO(repo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found")));
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO updateTransaction(int transactionId, TransactionCreateDTO dto) {
        Transaction transaction = repo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        transaction.setTransactionDate(dto.getTransactionDate());
        transaction.setTotalAmount(dto.getTotalAmount());
        transaction.setUser(user);

        return toDTO(repo.save(transaction));
    }

    @Override
    public void deleteTransaction(int transactionId) {
        repo.deleteById(transactionId);
    }

    private TransactionDTO toDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getTransactionDate(),
                transaction.getTotalAmount(),
                transaction.getUser().getId() // Long userId
        );
    }
}

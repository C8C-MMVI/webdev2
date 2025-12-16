package com.nav.agri.service.transaction;

import com.nav.agri.dto.transaction.TransactionRequestDTO;
import com.nav.agri.dto.transaction.TransactionResponseDTO;
import com.nav.agri.dto.transaction.TransactionItemDTO;
import com.nav.agri.models.Transaction;
import com.nav.agri.models.TransactionDetails;
import com.nav.agri.models.User;
import com.nav.agri.models.Product;
import com.nav.agri.repositories.TransactionRepository;
import com.nav.agri.repositories.UserRepository;
import com.nav.agri.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public TransactionServiceImpl(TransactionRepository transactionRepo, UserRepository userRepo, ProductRepository productRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionDate(request.getTransactionDate());
        transaction.setTotalAmount(request.getTotalAmount());

        // Map transaction items
        List<TransactionDetails> details = request.getItems().stream().map(item -> {
            Product product = productRepo.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            TransactionDetails td = new TransactionDetails();
            td.setProduct(product);
            td.setQuantity(item.getQuantity());
            td.setBasePrice(item.getBasePrice());
            td.setListPrice(item.getListPrice());
            td.setTransaction(transaction);
            return td;
        }).toList();

        transaction.setTransactionDetails(details);

        Transaction savedTransaction = transactionRepo.save(transaction);

        return mapToResponseDTO(savedTransaction);
    }

    @Override
    public TransactionResponseDTO getTransaction(int id) {
        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return mapToResponseDTO(transaction);
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionRepo.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponseDTO updateTransaction(int id, TransactionRequestDTO request) {
        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        transaction.setUser(user);
        transaction.setTransactionDate(request.getTransactionDate());
        transaction.setTotalAmount(request.getTotalAmount());

        // Clear existing details and replace with new ones
        transaction.getTransactionDetails().clear();

        List<TransactionDetails> updatedDetails = request.getItems().stream().map(item -> {
            Product product = productRepo.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            TransactionDetails td = new TransactionDetails();
            td.setProduct(product);
            td.setQuantity(item.getQuantity());
            td.setBasePrice(item.getBasePrice());
            td.setListPrice(item.getListPrice());
            td.setTransaction(transaction);
            return td;
        }).toList();

        transaction.setTransactionDetails(updatedDetails);

        Transaction savedTransaction = transactionRepo.save(transaction);

        return mapToResponseDTO(savedTransaction);
    }

    @Override
    public void deleteTransaction(int id) {
        transactionRepo.deleteById(id);
    }

    /* =====================
       Helper to map Transaction -> TransactionResponseDTO
    ===================== */
    private TransactionResponseDTO mapToResponseDTO(Transaction transaction) {
        TransactionResponseDTO response = new TransactionResponseDTO();
        response.setTransactionId(transaction.getTransactionId());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setTotalAmount(transaction.getTotalAmount());
        response.setUserId(transaction.getUser().getId());

        List<TransactionItemDTO> items = transaction.getTransactionDetails().stream().map(td -> {
            TransactionItemDTO itemDTO = new TransactionItemDTO();
            itemDTO.setProductId(td.getProduct().getProductId());
            itemDTO.setProductName(td.getProduct().getProductName());
            itemDTO.setQuantity(td.getQuantity());
            itemDTO.setBasePrice(td.getBasePrice());
            itemDTO.setListPrice(td.getListPrice());
            return itemDTO;
        }).toList();

        response.setItems(items);
        return response;
    }
}

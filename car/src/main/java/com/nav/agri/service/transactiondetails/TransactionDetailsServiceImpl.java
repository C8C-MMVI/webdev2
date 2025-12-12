package com.nav.agri.service.transactiondetails;

import com.nav.agri.dto.transactiondetails.TransactionDetailsCreateDTO;
import com.nav.agri.dto.transactiondetails.TransactionDetailsDTO;
import com.nav.agri.models.Product;
import com.nav.agri.models.Transaction;
import com.nav.agri.models.TransactionDetails;
import com.nav.agri.repositories.ProductRepository;
import com.nav.agri.repositories.TransactionDetailsRepository;
import com.nav.agri.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    private final TransactionDetailsRepository repo;
    private final TransactionRepository transactionRepo;
    private final ProductRepository productRepo;

    public TransactionDetailsServiceImpl(TransactionDetailsRepository repo,
                                         TransactionRepository transactionRepo,
                                         ProductRepository productRepo) {
        this.repo = repo;
        this.transactionRepo = transactionRepo;
        this.productRepo = productRepo;
    }

    @Override
    public TransactionDetailsDTO createTransactionDetails(TransactionDetailsCreateDTO dto) {
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Transaction transaction = transactionRepo.findById(dto.getTransactionId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        TransactionDetails td = new TransactionDetails();
        td.setQuantity(dto.getQuantity());
        td.setBasePrice(dto.getBasePrice());
        td.setListPrice(dto.getListPrice());
        td.setProduct(product);
        td.setTransaction(transaction);

        return toDTO(repo.save(td));
    }

    @Override
    public TransactionDetailsDTO getTransactionDetails(int id) {
        return toDTO(repo.findById(id)
                .orElseThrow(() -> new RuntimeException("TransactionDetails not found")));
    }

    @Override
    public List<TransactionDetailsDTO> getAllTransactionDetails() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDetailsDTO updateTransactionDetails(int id, TransactionDetailsCreateDTO dto) {
        TransactionDetails td = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("TransactionDetails not found"));
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Transaction transaction = transactionRepo.findById(dto.getTransactionId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        td.setQuantity(dto.getQuantity());
        td.setBasePrice(dto.getBasePrice());
        td.setListPrice(dto.getListPrice());
        td.setProduct(product);
        td.setTransaction(transaction);

        return toDTO(repo.save(td));
    }

    @Override
    public void deleteTransactionDetails(int id) {
        repo.deleteById(id);
    }

    private TransactionDetailsDTO toDTO(TransactionDetails td) {
        return new TransactionDetailsDTO(
                td.getTransactionDetailsId(),
                td.getQuantity(),
                td.getBasePrice(),
                td.getListPrice(),
                td.getProduct().getProductId(),
                td.getTransaction().getTransactionId()
        );
    }
}

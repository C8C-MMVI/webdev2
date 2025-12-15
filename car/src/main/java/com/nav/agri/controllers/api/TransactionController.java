package com.nav.agri.controllers.api;

import com.nav.agri.models.Transaction;
import com.nav.agri.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agri/transactions")
public class TransactionController {

    private final TransactionRepository repo;

    public TransactionController(TransactionRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Transaction get(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return repo.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable int id, @RequestBody Transaction transaction) {
        transaction.setTransactionId(id);
        return repo.save(transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }
}

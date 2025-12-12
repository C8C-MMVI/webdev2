package com.nav.agri.controllers.api;

import com.nav.agri.models.Transaction;
import com.nav.agri.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
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
    public Transaction get(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return repo.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        return repo.save(transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

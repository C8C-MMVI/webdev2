package com.nav.agri.controllers.api;

import com.nav.agri.models.TransactionDetails;
import com.nav.agri.repositories.TransactionDetailsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agri/transaction-details")
public class TransactionDetailsController {

    private final TransactionDetailsRepository repo;

    public TransactionDetailsController(TransactionDetailsRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<TransactionDetails> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public TransactionDetails get(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public TransactionDetails create(@RequestBody TransactionDetails details) {
        return repo.save(details);
    }

    @PutMapping("/{id}")
    public TransactionDetails update(@PathVariable int id, @RequestBody TransactionDetails details) {
        details.setTransactionDetailsId(id);
        return repo.save(details);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }
}

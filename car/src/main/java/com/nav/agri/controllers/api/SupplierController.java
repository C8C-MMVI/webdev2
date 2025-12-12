package com.nav.agri.controllers.api;

import com.nav.agri.models.Supplier;
import com.nav.agri.repositories.SupplierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierRepository repo;

    public SupplierController(SupplierRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Supplier getSupplier(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return repo.save(supplier);
    }

    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        return repo.save(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

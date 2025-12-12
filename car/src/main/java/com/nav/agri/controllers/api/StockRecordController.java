package com.nav.agri.controllers.api;

import com.nav.agri.models.StockRecord;
import com.nav.agri.repositories.StockRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-records")
public class StockRecordController {

    private final StockRecordRepository repo;

    public StockRecordController(StockRecordRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<StockRecord> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public StockRecord get(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public StockRecord create(@RequestBody StockRecord record) {
        return repo.save(record);
    }

    @PutMapping("/{id}")
    public StockRecord update(@PathVariable Long id, @RequestBody StockRecord record) {
        record.setId(id);
        return repo.save(record);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

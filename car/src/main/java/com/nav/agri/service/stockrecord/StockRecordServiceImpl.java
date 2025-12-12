package com.nav.agri.service.stockrecord;

import com.nav.agri.dto.stock.StockRecordCreateDTO;
import com.nav.agri.dto.stock.StockRecordDTO;
import com.nav.agri.models.Product;
import com.nav.agri.models.StockRecord;
import com.nav.agri.models.Supplier;
import com.nav.agri.repositories.ProductRepository;
import com.nav.agri.repositories.StockRecordRepository;
import com.nav.agri.repositories.SupplierRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository repo;
    private final ProductRepository productRepo;
    private final SupplierRepository supplierRepo;

    public StockRecordServiceImpl(StockRecordRepository repo, ProductRepository productRepo, SupplierRepository supplierRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public StockRecordDTO createStockRecord(StockRecordCreateDTO dto) {
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        StockRecord stockRecord = new StockRecord();
        stockRecord.setQuantity(dto.getQuantity());
        stockRecord.setUnitPrice(dto.getUnitPrice());
        stockRecord.setLastUpdated(dto.getLastUpdated());
        stockRecord.setProduct(product);
        stockRecord.setSupplier(supplier);

        return toDTO(repo.save(stockRecord));
    }

    @Override
    public StockRecordDTO getStockRecord(int id) {
        return toDTO(repo.findById(id)
                .orElseThrow(() -> new RuntimeException("StockRecord not found")));
    }

    @Override
    public List<StockRecordDTO> getAllStockRecords() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StockRecordDTO updateStockRecord(int id, StockRecordCreateDTO dto) {
        StockRecord stockRecord = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("StockRecord not found"));
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        stockRecord.setQuantity(dto.getQuantity());
        stockRecord.setUnitPrice(dto.getUnitPrice());
        stockRecord.setLastUpdated(dto.getLastUpdated());
        stockRecord.setProduct(product);
        stockRecord.setSupplier(supplier);

        return toDTO(repo.save(stockRecord));
    }

    @Override
    public void deleteStockRecord(int id) {
        repo.deleteById(id);
    }

    private StockRecordDTO toDTO(StockRecord sr) {
        return new StockRecordDTO(
                sr.getStockRecordId(),
                sr.getQuantity(),
                sr.getUnitPrice(),
                sr.getLastUpdated(),
                sr.getProduct().getProductId(),
                sr.getSupplier().getSupplierId()
        );
    }
}

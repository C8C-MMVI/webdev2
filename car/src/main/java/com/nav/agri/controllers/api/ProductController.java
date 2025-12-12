package com.nav.agri.controllers.api;

import com.nav.agri.models.Product;
import com.nav.agri.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setProductId(id);
        return repo.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        repo.deleteById(id);
    }
}

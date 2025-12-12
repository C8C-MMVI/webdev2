package com.nav.agri.service.product;

import com.nav.agri.dto.product.ProductCreateDTO;
import com.nav.agri.dto.product.ProductDTO;
import com.nav.agri.models.Category;
import com.nav.agri.models.Product;
import com.nav.agri.repositories.CategoryRepository;
import com.nav.agri.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;
    private final CategoryRepository categoryRepo;

    public ProductServiceImpl(ProductRepository repo, CategoryRepository categoryRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ProductDTO createProduct(ProductCreateDTO dto) {
        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setBasePrice(dto.getBasePrice());
        product.setListPrice(dto.getListPrice());
        product.setCategory(category);

        return toDTO(repo.save(product));
    }

    @Override
    public ProductDTO getProduct(int id) {
        return toDTO(repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(int id, ProductCreateDTO dto) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setBasePrice(dto.getBasePrice());
        product.setListPrice(dto.getListPrice());
        product.setCategory(category);

        return toDTO(repo.save(product));
    }

    @Override
    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    private ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getBasePrice(),
                product.getListPrice(),
                product.getCategory().getCategoryId()
        );
    }
}

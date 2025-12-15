package com.nav.agri.controllers.api;

import com.nav.agri.models.Category;
import com.nav.agri.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return repo.save(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        category.setCategoryId(id);
        return repo.save(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        repo.deleteById(id);
    }
}

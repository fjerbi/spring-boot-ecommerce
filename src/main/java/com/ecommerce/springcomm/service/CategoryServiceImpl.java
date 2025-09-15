package com.ecommerce.springcomm.service;

import com.ecommerce.springcomm.model.Category;
import com.ecommerce.springcomm.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public void createCategory(Category category) {
        // Let Hibernate generate the ID
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categoryRepository.delete(category);
        return "Category with ID " + categoryId + " deleted!";
    }

    @Override
    @Transactional
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }
}

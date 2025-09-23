package com.ecommerce.springcomm.service;

import com.ecommerce.springcomm.exceptions.ApiException;
import com.ecommerce.springcomm.exceptions.ResourceNotFoundException;
import com.ecommerce.springcomm.model.Category;
import com.ecommerce.springcomm.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty())
            throw new ApiException("No category created until now");
        return categories;
    }

    @Override
    @Transactional
    public void createCategory(Category category) {
        // Let Hibernate generate the ID
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null)
            throw new ApiException("Category with the name" + category.getCategoryName() + "already exists");
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(category);
        return "Category with ID " + categoryId + " deleted!";
    }

    @Override
    @Transactional
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));

        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }
}

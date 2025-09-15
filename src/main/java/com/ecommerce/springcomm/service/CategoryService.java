/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.springcomm.service;

import com.ecommerce.springcomm.model.Category;
import java.util.List;

/**
 *
 * @author Firas
 */
public interface CategoryService {

    List<Category> getAllCategories();

    void createCategory(Category category);
    
    String deleteCategory(Long categoryId);
    
     Category updateCategory(Category category, Long categoryId);

}

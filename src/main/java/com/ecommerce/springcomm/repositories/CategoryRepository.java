/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.springcomm.repositories;

import com.ecommerce.springcomm.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Firas
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{

    Category findByCategoryName(String categoryName);
}

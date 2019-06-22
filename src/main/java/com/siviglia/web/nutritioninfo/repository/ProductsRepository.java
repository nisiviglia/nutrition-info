/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file ProductsRepository.java
 * @brief Repo for the products model.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-06-04
 */

package com.siviglia.web.nutritioninfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siviglia.web.nutritioninfo.model.Products;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Integer>{
    
}

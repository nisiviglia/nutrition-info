/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/// @file ProductsDTO.java
/// @brief Object for reterving information from a search for products such as total results and actual results.
/// @author Nicholas Siviglia
/// @version 1.0
/// @date 2019-10-03

package com.siviglia.web.nutritioninfo.repository;

import java.util.List;

import com.siviglia.web.nutritioninfo.model.Products;

public class ProductsDTO {
    
    private List<Products> products;
    private long totalResults;

    ProductsDTO(List<Products> products, long totalResults){
        this.products = products;
        this.totalResults = totalResults;
    }

    public void setProducts(List<Products> products){
        this.products = products;
    }

    public List<Products> getProducts(){
        return this.products;
    }

    public void setTotalResults(long totalResults){
        this.totalResults = totalResults;
    }

    public long getTotalResults(){
        return totalResults;
    }
}

/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file IndexController.java
 * @brief main controller for this project.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-05-30
 */

package com.siviglia.web.nutritioninfo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.siviglia.web.nutritioninfo.model.Products;
import com.siviglia.web.nutritioninfo.repository.ProductSearchService;

@RestController
class IndexController{
    
    @Autowired
    private ProductSearchService productSearchService;

    @CrossOrigin
    @RequestMapping(value= "/api/v1/search/name/{name}",
        method= RequestMethod.GET,
        produces= "application/json")
    public @ResponseBody List<Products> search(@PathVariable() String name){
        
        return productSearchService.searchProductsByKeywordQuery(name);
    }
}

/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file SearchController.java
 * @brief controller that handles all search functions.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-06-28
 */

package com.siviglia.web.nutritioninfo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.siviglia.web.nutritioninfo.model.Products;
import com.siviglia.web.nutritioninfo.repository.ProductSearchService;
import com.siviglia.web.nutritioninfo.repository.ProductsRepository;
import com.siviglia.web.nutritioninfo.repository.ProductsDTO;
import com.siviglia.web.nutritioninfo.exception.NotFoundException;

@RestController
public class SearchController{

    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private ProductsRepository productsRepository;

    @CrossOrigin
    @RequestMapping(value= "/api/v1/search/name/{name}",
        method= RequestMethod.GET,
        produces= "application/json")
    public @ResponseBody Map<String, Object> search(
            @PathVariable() String name,
            @RequestParam(name= "first_result", defaultValue="0") int firstResult,
            @RequestParam(name= "max_results", defaultValue="20") int maxResults){

        //Keep max results less than 100.
        if(maxResults > 100){
            maxResults = 100;
        }
        
        //Grab search results
        ProductsDTO productsDTO = 
            productSearchService.searchProductsByKeyword(
                    name, maxResults, firstResult);
        
        //Create json results
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("search_query", name);
        map.put("total_results", productsDTO.getTotalResults() );
        map.put("max_results", maxResults);
        map.put("returned_results", productsDTO.getProducts().size() );
        map.put("first_result", firstResult);
        map.put("products", productsDTO.getProducts() );

        return map;
    }

    @CrossOrigin
    @RequestMapping(value= "/api/v1/search/lowfat/name/{name}",
        method= RequestMethod.GET,
        produces= "application/json")
    public @ResponseBody Map<String, Object> searchLowFat(
            @PathVariable() String name,
            @RequestParam(name= "first_result", defaultValue="0") int firstResult,
            @RequestParam(name= "max_results", defaultValue="20") int maxResults,
            @RequestParam(name= "at_most", defaultValue="10.0") double atMostFat){

        //Keep max results less than 100.
        if(maxResults > 100){
            maxResults = 100;
        }
        
        //Grab search results
        ProductsDTO productsDTO = 
            productSearchService.searchProductsByLowFatAndKeyword(
                    name, maxResults, firstResult, atMostFat);
        
        //Create json results
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("search_query", name);
        map.put("total_results", productsDTO.getTotalResults() );
        map.put("max_results", maxResults);
        map.put("returned_results", productsDTO.getProducts().size() );
        map.put("first_result", firstResult);
        map.put("products", productsDTO.getProducts() );

        return map;
    }

    @CrossOrigin
    @RequestMapping(value= "/api/v1/product/ndbnumber/{ndbNumber}",
        method= RequestMethod.GET,
        produces= "application/json")
    public @ResponseBody Products getProduct(@PathVariable() int ndbNumber){
        
        Products product = productsRepository
            .findById( ndbNumber )
            .orElseThrow( NotFoundException::new );

        return product;
    }

}

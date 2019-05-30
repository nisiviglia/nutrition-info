/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file ProductSearchService.java
 * @brief Uses hibernate-search to index and search the products table.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-05-30
 */

package com.siviglia.web.nutritioninfo.repository;

import org.apache.lucene.search.Query;
import org.hibernate.search.engine.ProjectionConstants;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import com.siviglia.web.nutritioninfo.model.Products;

@Repository
@Transactional
public class ProductSearchService {
    
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Products> searchProductsByKeywordQuery(String text){
        
        Query keywordQuery = getQueryBuilder()
            .keyword()
            .onField("longName")
            .matching(text)
            .createQuery();

        List<Products> products = getJpaQuery(keywordQuery).getResultList();
        return products;
    }
	
    private QueryBuilder getQueryBuilder() {

        FullTextEntityManager fullTextEntityManager = 
			Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder()
            .forEntity(Products.class)
            .get();
    }

	private FullTextQuery getJpaQuery(Query luceneQuery) {

        FullTextEntityManager fullTextEntityManager = 
			Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager
			.createFullTextQuery(luceneQuery, Products.class);
    }

}

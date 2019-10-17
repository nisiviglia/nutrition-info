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
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.index.Term;
import org.springframework.stereotype.Repository;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.backend.lucene.LuceneExtension;

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

    //@SuppressWarnings("unchecked")
    public ProductsDTO searchProductsByKeyword(String text, int maxResults, int firstResult){
            
        SearchSession searchSession = Search.session( entityManager );
        SearchResult<Products> results = searchSession.search( Products.class )
            .predicate( f -> f.match()
                        .fields("longName", "manufacturer")
                        .matching(text)
                    )
                    .fetch(firstResult, maxResults);

        return new ProductsDTO(results.getHits(), results.getTotalHitCount());
    }

    public ProductsDTO searchProductsByLowFatAndKeyword(String text, int maxResults, int firstResult, double atMostFat){

        SearchSession searchSession = Search.session( entityManager );
        SearchResult<Products> results = searchSession.search( Products.class )
            .predicate( f -> f.bool()
                        .must( f.match().fields("longName", "manufacturer").matching(text) )
                        .must( f.nested().objectField("nutrients")
                            .nest( f.bool() 
                                .must( f.match().field("nutrients.nutrientCode").matching(1004) ) //1004 is Total liptid (fat)
                                .must( f.range().field("nutrients.outputValue").atMost( atMostFat ) )
                            )
                        )
                    )
                    .fetch(firstResult, maxResults);

        return new ProductsDTO(results.getHits(), results.getTotalHitCount());
    }
    
    public ProductsDTO searchProductsByHeartHealthyAndKeyword(String text, int maxResults, int firstResult, double atMostSalt, double atMostChol){

        SearchSession searchSession = Search.session( entityManager );
        SearchResult<Products> results = searchSession.search( Products.class )
            .predicate( f -> f.bool()
                        .must( f.match().fields("longName", "manufacturer").matching(text) )
                        .must( f.nested().objectField("nutrients")
                            .nest( f.bool() 
                                .must( f.match().field("nutrients.nutrientCode").matching(1093) ) //1253 is Sodium NA 
                                .must( f.range().field("nutrients.outputValue").atMost( atMostSalt ) )
                            )
                        )
                        .must( f.nested().objectField("nutrients")
                            .nest( f.bool() 
                                .must( f.match().field("nutrients.nutrientCode").matching(1253) ) //1253 is Cholesterol
                                .must( f.range().field("nutrients.outputValue").atMost( atMostChol ) )
                            )
                        )
                    )
                    .fetch(firstResult, maxResults);

        return new ProductsDTO(results.getHits(), results.getTotalHitCount());
    }

    public ProductsDTO searchProductsByLowSugarAndKeyword(String text, int maxResults, int firstResult, double atMostSugar){

        SearchSession searchSession = Search.session( entityManager );
        SearchResult<Products> results = searchSession.search( Products.class )
            .predicate( f -> f.bool()
                        .must( f.match().fields("longName", "manufacturer").matching(text) )
                        .must( f.nested().objectField("nutrients")
                            .nest( f.bool() 
                                .must( f.match().field("nutrients.nutrientCode").matching(2000) ) //2000 is Sugars 
                                .must( f.range().field("nutrients.outputValue").atMost( atMostSugar ) )
                            )
                        )
                    )
                    .fetch(firstResult, maxResults);

        return new ProductsDTO(results.getHits(), results.getTotalHitCount());
    }

    public ProductsDTO searchProductsByLowCarbAndKeyword(String text, int maxResults, int firstResult, double atMostCarbs){

        SearchSession searchSession = Search.session( entityManager );
        SearchResult<Products> results = searchSession.search( Products.class )
            .predicate( f -> f.bool()
                        .must( f.match().fields("longName", "manufacturer").matching(text) )
                        .must( f.nested().objectField("nutrients")
                            .nest( f.bool() 
                                .must( f.match().field("nutrients.nutrientCode").matching(1005) ) // 1005 is Carbs
                                .must( f.range().field("nutrients.outputValue").atMost( atMostCarbs ) )
                            )
                        )
                    )
                    .fetch(firstResult, maxResults);

        return new ProductsDTO(results.getHits(), results.getTotalHitCount());
    }

    public ProductsDTO searchProductsByLowCalAndKeyword(String text, int maxResults, int firstResult, double atMostCal){

        SearchSession searchSession = Search.session( entityManager );
        SearchResult<Products> results = searchSession.search( Products.class )
            .predicate( f -> f.bool()
                        .must( f.match().fields("longName", "manufacturer").matching(text) )
                        .must( f.nested().objectField("nutrients")
                            .nest( f.bool() 
                                .must( f.match().field("nutrients.nutrientCode").matching(1008) ) // 1008 is Energy
                                .must( f.range().field("nutrients.outputValue").atMost( atMostCal ) )
                            )
                        )
                    )
                    .fetch(firstResult, maxResults);

        return new ProductsDTO(results.getHits(), results.getTotalHitCount());
    }
}

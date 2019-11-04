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

    public ProductsDTO searchProductsWithConstraints(String text, int maxResults, int firstResult, List<ProductSearchConstraint> constraints){

        SearchSession searchSession = Search.session( entityManager );
        SearchResult<Products> results = searchSession.search( Products.class )
            .predicate( f -> f.bool(b -> {

                    b.must( f.match().fields("longName", "manufacturer").matching(text) );
                    
                    for(ProductSearchConstraint c : constraints){

                        b.must( f.nested().objectField("nutrients")             
                        .nest( f.bool( nb -> {
                            
                            nb.must( f.match().field("nutrients.nutrientCode").matching( c.getCode() ));

                            if(c.getIsInclusiveLower() == true){
                            
                                nb.must( f.range().field("nutrients.outputValue").atMost( c.getAmount() ));
                            } else {
                            
                                nb.must( f.range().field("nutrients.outputValue").atLeast( c.getAmount() ));
                            }
                        }))
                        ); 
                    
                    } //end for loop

                })).fetch(firstResult, maxResults);

        return new ProductsDTO(results.getHits(), results.getTotalHitCount());
    }
}

/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/// @file ProductsLuceneAnalysisConfigurer.java
/// @brief Hibernate ORM + Lucene analysis configurer for the Products model
/// @author Nicholas Siviglia
/// @version 1.0
/// @date 2019-10-03

package com.siviglia.web.nutritioninfo.model;

import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;

public class ProductsLuceneAnalysisConfigurer implements LuceneAnalysisConfigurer {

    @Override
        public void configure(LuceneAnalysisConfigurationContext context) {
                context.analyzer( "english" ).custom() 
                        .tokenizer( StandardTokenizerFactory.class ) 
                        .tokenFilter( LowerCaseFilterFactory.class ) 
                        .tokenFilter( SnowballPorterFilterFactory.class ) 
                                .param( "language", "English" ) 
                        .tokenFilter( ASCIIFoldingFilterFactory.class );
        
            }
}

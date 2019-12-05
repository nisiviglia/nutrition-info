/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/

package com.siviglia.web.nutritioninfo.repository;

import java.io.Serializable;
import java.util.List;

public class ProductSearchConstraintDTO implements Serializable {

    private static final long serialVersionUID = -519932722874612835L;
    private List<ProductSearchNutrientConstraint> nutrientConstraints;
    private List<ProductSearchIngredientConstraint> ingredientConstraints;

    public void setNutrientConstraints(List<ProductSearchNutrientConstraint> nutrientConstraints){
        this.nutrientConstraints = nutrientConstraints;
    }

    public List<ProductSearchNutrientConstraint> getNutrientConstraints(){
        return this.nutrientConstraints;
    }

    public void setIngredientConstraints(List<ProductSearchIngredientConstraint> ingredientConstraints){
        this.ingredientConstraints = ingredientConstraints;
    }

    public List<ProductSearchIngredientConstraint> getIngredientConstrants(){
        return this.ingredientConstraints;
    }
}

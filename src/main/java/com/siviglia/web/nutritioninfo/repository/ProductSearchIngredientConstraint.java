/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/

package com.siviglia.web.nutritioninfo.repository;

import java.io.Serializable;

public class ProductSearchIngredientConstraint implements Serializable {


    private static final long serialVersionUID = -519931722884602033L;
    private String name;
    private boolean isIncluded;

    public ProductSearchIngredientConstraint(String name, boolean isIncluded){
        this.name = name;
        this.isIncluded = isIncluded;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setIsIncluded(boolean isIncluded){
        this.isIncluded = isIncluded;
    }

    public boolean getIsIncluded(){
        return this.isIncluded;
    }

    @Override 
    public String toString(){
        return String.format("[name=%s, isIncluded=%b]", this.name, this.isIncluded);
    }
}

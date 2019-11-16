/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file ProductSearchService.java
 * @brief Uses hibernate-search to index and search the products table.
 * @author Nicholas Siviglia
 */

package com.siviglia.web.nutritioninfo.repository;

import java.io.Serializable;

public class ProductSearchConstraint implements Serializable {
    
    private static final long serialVersionUID = -679991422884602033L;
    private String id;
    private int code;
    private boolean isInclusiveLower;
    private double amount;

    public ProductSearchConstraint(){}

    public ProductSearchConstraint(int code, boolean isInclusiveLower, int amount){

        this("0", code, isInclusiveLower, amount);
    }

    public ProductSearchConstraint(String id, int code, boolean isInclusiveLower, int amount){
        this.id = id;
        this.code = code;
        this.isInclusiveLower = isInclusiveLower;
        this.amount = amount;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setCode(int code){
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public void setIsInclusiveLower(boolean isInclusiveLower){
        this.isInclusiveLower = isInclusiveLower;
    }

    public boolean getIsInclusiveLower(){
        return isInclusiveLower;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return this.amount;
    }

    @Override
    public String toString(){
        return String.format("[id=%s, code= %d , isInclusiveLower= %b , amount= %f]"
                , this.id, this.code, this.isInclusiveLower, this.amount);
    }
}

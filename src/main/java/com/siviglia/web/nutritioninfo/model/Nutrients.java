/**
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file Nutrients.java
 * @brief nutrients model.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-05-30
 */

package com.siviglia.web.nutritioninfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name= "nutrients") 
public class Nutrients{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nutrient_id")
    private long nutrientId;

    @Column(name="nutrient_code")
    private int nutrientCode;

    @Column(name="nutrient_name")
    private String nutrientName;

    @Column(name="output_value")
    private float outputValue;

    @Column(name="output_uom")
    private String outputUOM;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name= "ndb_number")
    private Products products;

    @JsonIgnore
    public long getNutrientId(){
        return nutrientId;
    }

    public void setNutrientId(long nutrientId){
        this.nutrientId = nutrientId; 
    }

    @JsonIgnore
    public int getNutrientCode(){
        return nutrientCode;
    }

    public void setNutrientCode(int nutrientCode){
        this.nutrientCode = nutrientCode;
    }

    public String getNutrientName(){
        return nutrientName;
    }

    public void setNutrientName(String nutrientName){
        this.nutrientName = nutrientName;
    }

    public float getOutputValue(){
        return outputValue;
    }

    public void setOutputValue(float outputValue){
        this.outputValue = outputValue;
    }
    
    public String getOutputUOM(){
        return outputUOM;
    }

    public void setOutputUOM(String outputUOM){
        this.outputUOM = outputUOM;
    }

    @JsonIgnore
    public Products getProducts(){
        return products;
    }

    public void setProducts(Products products){
        this.products = products;
    }

}

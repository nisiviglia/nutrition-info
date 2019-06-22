/**
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file ServingSize.java
 * @brief serving size model.
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
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name= "serving_size") 
class ServingSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="serving_id")
    private int servingId;

    @Column(name="serving_size")
    private String ServingSize;

    @Column(name="serving_size_uom")
    private String servingSizeUOM;

    @Column(name="household_serving_size")
    private String householdServingSize;

    @Column(name="household_serving_size_uom")
    private String householdServingSizeUOM;

    @Column(name="preparation_state")
    private String preparationState;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name= "ndb_number")
    private Products products;

    @JsonIgnore
    public int getServingId(){
        return servingId;
    }

    public void setServingId(int servingId){
        this.servingId = servingId;
    }

    public String getServingSize(){
        return ServingSize;
    }

    public void setServingSize(String ServingSize){
        this.ServingSize = ServingSize;
    }

    public String getServingSizeUOM(){
        return servingSizeUOM;
    }

    public void setServeringSizeUOM(String servingSizeUOM){
        this.servingSizeUOM = servingSizeUOM;
    }

    public String getHouseholdServingSize(){
        return householdServingSize;
    }

    public void setHouseholdServingSize(String householdServingSize){
        this.householdServingSize = householdServingSize;
    }

    public String getHouseholdServingSizeUOM(){
        return householdServingSizeUOM;
    }

    public void setHouseholdServingSizeUOM(String householdServingSizeUOM){
        this.householdServingSizeUOM = householdServingSizeUOM;
    }

    @JsonIgnore
    public String getPreparationState(){
        return preparationState;
    }

    public void setPreparationState(String preparationState){
        this.preparationState = preparationState;
    }

    @JsonIgnore
    public Products getProducts(){
        return products;
    }

    public void setProducts(Products products){
        this.products = products;
    }
}

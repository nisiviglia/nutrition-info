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

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name= "serving_size") 
class ServingSize {

    @Id
    @Column(name="NDB_No")
    private int ndbNo;

    @Column(name="Serving_Size")
    private String ServingSize;

    @Column(name="Serving_Size_UOM")
    private String servingSizeUOM;

    @Column(name="Household_Serving_Size")
    private String householdServingSize;

    @Column(name="Household_Serving_Size_UOM")
    private String householdServingSizeUOM;

    @Column(name="Preparation_State")
    private String preparationState;

    public int getNDBNo(){
        return ndbNo;
    }

    public void setNDBNo(int ndbNo){
        this.ndbNo = ndbNo;
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

    public String getPreparationState(){
        return preparationState;
    }

    public void setPreparationState(String preparationState){
        this.preparationState = preparationState;
    }
}

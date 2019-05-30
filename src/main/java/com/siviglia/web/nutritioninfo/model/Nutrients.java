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

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name= "nutrients") 
class Nutrients {
    
    @Id
    @Column(name="NDB_No")
    private int ndbNo;

    @Column(name="Nutrient_Code")
    private int nutrientCode;

    @Column(name="Nutrient_Name")
    private String nutrientName;

    @Column(name="Derivation_Code")
    private String derivationCode;

    @Column(name="Output_Value")
    private float outputValue;

    @Column(name="Output_UOM")
    private String outputUOM;
    
    public int getNDBNo(){
        return ndbNo;
    }

    public void setNDBNo(int ndbNo){
        this.ndbNo = ndbNo;
    }

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

    public String getDerivationCode(){
        return derivationCode;
    }

    public void setDerivationCode(String derivationCode){
        this.derivationCode = derivationCode;
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
}

/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file Derivations.java
 * @brief derivations model.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-05-30
 */

package com.siviglia.web.nutritioninfo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="derivations")
public class Derivations{
    
    @Id
    @Column(name="Derivation_Code")
    private String derivationCode;

    @Column(name="Derivation_Code_Description")
    private String derivationCodeDescription;

    public String getDerivationCode(){
        return derivationCode;
    }

    public void setDerivationCode(String derivationCode){
        this.derivationCode = derivationCode;
    }

    public String getDerivationCodeDescription(){
        return derivationCodeDescription;
    }

    public void setDerivationCodeDescription(String derivationCodeDescription){
        this.derivationCodeDescription = derivationCodeDescription;
    }
}

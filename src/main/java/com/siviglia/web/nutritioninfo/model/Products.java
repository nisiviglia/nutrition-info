/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file Products.java
 * @brief products model. Use this one to pull information.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-05-30
 */

package com.siviglia.web.nutritioninfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Store;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;

import java.util.Date;
import java.util.List;

@Entity
@Indexed
@Table(name= "products") 
public class Products {
    
    @Id
    @Column(name="ndb_number")
    private int ndbNumber;

    @Column(name="long_name")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String longName;

    @Column(name="gtin_upc")
    private String gtinUPC;

    @Column(name="manufacturer")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String manufacturer;
    
    @Column(name="date_modified")
    private Date dateModified;
    
    @Column(name="date_available")
    private Date dateAvailable;

    @Column(name="ingredients_english", columnDefinition="MEDIUMTEXT")
    private String ingredientsEnglish;

    @Column(name="serving_size")
    private String ServingSize;

    @Column(name="serving_size_uom")
    private String servingSizeUOM;

    @Column(name="household_serving_size_fulltext")
    private String householdServingSizeFulltext;

    @OneToMany(mappedBy= "products", fetch= FetchType.LAZY)
    private List<Nutrients> nutrients;

    public int getNDBNumber(){
        return ndbNumber;
    }

    public void setNDBNumber(int ndbNumber){
        this.ndbNumber = ndbNumber;
    }

    public String getLongName(){
        return longName;
    }

    public void setLongName(String longName){
        this.longName = longName;
    }

    public String getGtinUPC(){
        return gtinUPC; 
    }

    public void setGtinUPC(String gtinUPC){
        this.gtinUPC = gtinUPC;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    @JsonIgnore
    public Date getDateModified(){
        return dateModified;
    }

    public void setDateModified(Date dateModified){
        this.dateModified = dateModified;
    }

    @JsonIgnore
    public Date getDateAvailable(){
        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable){
        this.dateAvailable = dateAvailable;
    }
    
    public String getIngredientsEnglish(){
        return ingredientsEnglish;
    }

    public void setIngredientsEnglish(String ingredientsEnglish){
        this.ingredientsEnglish = ingredientsEnglish;
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

    public String getHouseholdServingSizeFulltext(){
        return householdServingSizeFulltext;
    }

    public void setHouseholdServingSizeFulltext(String householdServingSizeFulltext){
        this.householdServingSizeFulltext = householdServingSizeFulltext;
    }

    public List<Nutrients> getNutrients(){
        return nutrients;
    }

    public void setNutrients(List<Nutrients> nutrients){
        this.nutrients = nutrients;
    }
}

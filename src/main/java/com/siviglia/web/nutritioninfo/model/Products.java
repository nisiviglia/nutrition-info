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

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

import java.util.Date;

@Entity
@Indexed
@Table(name= "products") 
public class Products {
    
    @Id
    @DocumentId
    @Column(name="NDB_Number")
    private int ndbNumber;

    @Column(name="long_name")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String longName;

    @Column(name="data_source")
    private String dataSource;

    @Column(name="gtin_upc")
    private String gtinUPC;

    @Column(name="manufacturer")
    private String manufacturer;
    
    @Column(name="date_modified")
    private Date dateModified;
    
    @Column(name="date_available")
    private Date dateAvailable;

    @Column(name="ingredients_english")
    private String ingredientsEnglish;

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

    public String getDataSource(){
        return dataSource;
    }

    public void ssetDataSource(String dataSource){
        this.dataSource = dataSource;
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

    public Date getDateModified(){
        return dateModified;
    }

    public void setDateModified(Date dateModified){
        this.dateModified = dateModified;
    }

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
}

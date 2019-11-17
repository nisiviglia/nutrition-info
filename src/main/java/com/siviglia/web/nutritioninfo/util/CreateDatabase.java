/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file CreateDatabaseFromFolder.java
 * @brief This takes csv files and creates the remote database.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-06-30
 */

package com.siviglia.web.nutritioninfo.util;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.File;

import com.siviglia.web.nutritioninfo.model.Products;
import com.siviglia.web.nutritioninfo.model.Nutrients;

/*
 * This is a quick and dirty app to create the backend database from
 * the supplied csv files.
 *
 * 1. Open a connection the the remote database using hibernate ORM.
 *
 * 2. Create a temp database using H2 Embedded.
 *
 * 3. Populate the temp database with the csv files.
 *
 * 4. Map the temp database to Java objects and store them in the remote database
 *    in batches.
 *
 * 5. Drop everything from the temp database and close all connections.
*/
public class CreateDatabase {
    
    public static void main( String[] args ) throws SQLException {
        
        /*******************************************************************************
         * 
         * 1. Open a connection the the remote database using hibernate ORM.
         *
        *******************************************************************************/

        SessionFactory factory;

        try {
            File file = new File("target/classes/hibernate.cfg.xml");
            factory = new Configuration().configure(file).buildSessionFactory();

        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        } 

        /*******************************************************************************
         * 
         * 2. Create a temp database using H2 Embedded.
         *
        *******************************************************************************/

        Connection conn = DriverManager.getConnection("jdbc:h2:file:/tmp/createDatabase", "", "");
        Statement stmt = conn.createStatement();
        stmt.execute("drop all objects");
        System.out.println("Local database connection opened.");    
        
        /*******************************************************************************
         * 
         * 3. Populate the temp database with the csv files.
         *
        *******************************************************************************/

        String path = "~/Desktop/FoodData_Central_csv_2019-10-11";

        System.out.println("Loading branded_food...");
        stmt.execute(String.format("create table branded_food as select * from csvread('%s/branded_food.csv');", path));
        stmt.execute("create index fdc_id_branded on branded_food(fdc_id)");

        System.out.println("Loading food...");
        stmt.execute(String.format("create table food as select * from csvread('%s/food.csv');", path));
        stmt.execute("create index fdc_id_food on food(fdc_id)");
        
        System.out.println("Loading food_nutrient...");
        stmt.execute(String.format("create table food_nutrient as select * from csvread('%s/food_nutrient.csv');", path));
        stmt.execute("create index fdc_id_food_nutrient on food_nutrient(fdc_id)");

        System.out.println("Loading nutrient...");
        stmt.execute(String.format("create table nutrient as select * from csvread('%s/nutrient.csv');", path));

        /*******************************************************************************
         * 
         * 4. Map the temp database to Java objects and store them in the remote database
         *    in batches.
         *
        *******************************************************************************/

        //Get a list of unique ids to use for pulling info for each product.
        System.out.println("Getting list of fdc_ids...");
        ResultSet fdc_ids = stmt.executeQuery("select fdc_id from branded_food"); 

        //map local db to remote db using ORM.
        System.out.println("Creating remote database...");
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        for(int i=0; fdc_ids.next(); i++){
            ResultSet rs = conn.createStatement().executeQuery(
                    String.format("select * from branded_food join food on branded_food.fdc_id = food.fdc_id where branded_food.fdc_id = %s", fdc_ids.getString("fdc_id") ));
            ResultSet rs2 = conn.createStatement().executeQuery(
                    String.format("select * from food_nutrient join nutrient on food_nutrient.nutrient_id = nutrient.id where food_nutrient.fdc_id = %s", fdc_ids.getString("fdc_id") ));

            rs.next();
            Products product = new Products();
            product.setNDBNumber(rs.getInt("fdc_id"));
            product.setLongName(rs.getString("description"));
            product.setGtinUPC(rs.getString("gtin_upc"));
            product.setManufacturer(rs.getString("brand_owner"));
            product.setDateModified(rs.getDate("modified_date"));
            product.setDateAvailable(rs.getDate("available_date"));
            product.setIngredientsEnglish(rs.getString("ingredients"));
            product.setServingSize(rs.getString("serving_size"));
            product.setServeringSizeUOM(rs.getString("serving_size_unit"));
            product.setHouseholdServingSizeFulltext(rs.getString("household_serving_fulltext"));
            
            List<Nutrients> nutrientList = new ArrayList<Nutrients>();
            while(rs2.next()){
                Nutrients nutrients = new Nutrients();
                nutrients.setNutrientCode(rs2.getInt("nutrient_id"));
                nutrients.setNutrientName(rs2.getString("name"));
                nutrients.setOutputUOM(rs2.getString("unit_name"));

                //the csv files have the amount scaled to 100 grams.
                //I want to store the amount scaled to the serving size.
                float temp = (rs.getFloat("serving_size") / 100.0F) * rs2.getFloat("amount");
                nutrients.setOutputValue(temp);

                //for the many-to-one relationship you need to set both sides
                //then save both side.
                //nutrient 1-->1 product
                //prodcut 1-->m nutrients
                nutrients.setProducts(product);
                session.save(nutrients);
                nutrientList.add(nutrients);
            }
            product.setNutrients(nutrientList);
            session.save(product);  

            //Batch together.
            if(i % 500 == 0){
                session.flush();
                session.clear();
                tx.commit();
                tx = session.beginTransaction();
            }
        }
        
        /*******************************************************************************
         * 
         * 5. Drop everything from the temp database and close all connections.
         *
        *******************************************************************************/

        System.out.println("Cleaning up...");
        stmt.execute("drop all objects");
        stmt.closeOnCompletion();
        tx.commit();
        session.close();
        conn.close();
    }
}


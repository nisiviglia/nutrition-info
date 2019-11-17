import React from 'react';
import './NutritionFacts.css';
import * as dailyValues from './DailyValues'; 

const NutritionFacts = props => (
    <div className="nutrition-facts-container">
        <div className="nutrition-facts-first">
            <h2>Nutrition Facts</h2>
            <p>Serving Size: {props.product.householdServingSizeFulltext} ({props.product.servingSize}{props.product.servingSizeUOM})</p>
        </div>
        <div className="nutrition-facts-second">
            <table className="tg">
                <tbody>
                    <tr>
                      <th className="tg-title">Amount Per Serving</th>
                      <th className="tg-title">% Daily Values</th>
                    </tr>
                    <div className="nutrition-facts-second-nutrients">
                        {dailyValues.nutrients.map(n => getNutrient(props.product.nutrients, n, props.product.servingSize))}
                    </div>
                        {dailyValues.vitamins.map(v => getNutrient(props.product.nutrients, v, props.product.servingSize))}
                        {props.product.nutrients.map(v => getRemainingNutrient(v, props.product.servingSize))}
                </tbody>
		    </table> 
        </div>
        <div className="nutrition-facts-third">
            <p><b>Ingredients: </b>{props.product.ingredientsEnglish}</p>
        </div>
    </div>
);

//This finds the nutrient in the list.
//Finally, it removes the nutrient from the list so it is not added again!
function getNutrient(array, dailyValue, servingSize) {
    let i = array.findIndex(n => n.nutrientName === dailyValue.dbName);
    let total = dailyValue.displayName;
    let percent = ""; 
    let outputValue = "";

    if( i !== -1){

        if(dailyValue.dailyValue !== -1){
            percent = array[i].outputValue.toFixed(0) + "%"; 
        }
        
        if(dailyValue.bold === true){
           total =  <b>{total}</b>;
            percent = <b>{percent}</b>;
        }

        outputValue = array[i].outputValue.toFixed(1) + " " + array[i].outputUOM.toLowerCase(); 

        array.splice(i, 1);
        return (
            <tr key={dailyValue.displayName}>
              <td className="tg-dailyValue">{total} {outputValue}</td>
              <td className="tg-dailyValue">{percent}</td>
            </tr>
        );
    }
    
    return;
}

//this function is called after all the nutrients with dauily values are 
//displayed and removed from the list. This displays any remaining nutrients and 
//their value.
function getRemainingNutrient(n, servingSize){
   return (
        <tr key={n.nutrientName}>
          <td className="tg-noDailyvalue">{n.nutrientName + " " + n.outputValue.toFixed(1) + " " + n.outputUOM.toLowerCase()}</td>
          <td className="tg-noDailyValue"></td>
        </tr>
   ) 
}

export default NutritionFacts;

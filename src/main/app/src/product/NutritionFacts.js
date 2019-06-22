import React from 'react';
import * as dailyValues from './DailyValues'; 

const NutritionFacts = props => (
    <div className="nutrition-facts-container">
        <div className="nutrition-facts-first">
            <h1>{props.product.longName}</h1>
            <p>{props.product.manufacturer}</p>
            <p>upc: {props.product.gtinUPC}</p>
            <h2>Nutrition Facts</h2>
            <p>Serving Size: {props.product.servingSize.householdServingSize} {props.product.servingSize.householdServingSizeUOM}</p>
        </div>
        <div className="nutrition-facts-second">
            <table className="tg">
                <tbody>
                    <tr>
                      <th className="tg-0lax">Amount Per Serving</th>
                      <th className="tg-0lax">% Daily Values</th>
                    </tr>
                    {dailyValues.nutrients.map(n => getNutrient(props.product.nutrients, n))}
                    {dailyValues.vitamins.map(v => getNutrient(props.product.nutrients, v))}
                    {props.product.nutrients.map(v => getRemainingNutrient(v))}
                </tbody>
		    </table> 
        </div>
        <div className="nutrition-facts-fourth">
            <p>Ingredients: {props.product.ingredientsEnglish}</p>
        </div>
    </div>
);

function getNutrient(array, dailyValue) {
    let i = array.findIndex(n => n.nutrientName === dailyValue.dbName);
    let total = "";
    let percent = ""; 

    if( i !== -1){
        total = dailyValue.displayName + " " + array[i].outputValue + " " + array[i].outputUOM; 

        if(dailyValue.dailyValue !== -1){
            percent = ((array[i].outputValue / dailyValue.dailyValue) * 100).toFixed(2) + "%"; 
        }
        array.splice(i, 1);

        return (
            <tr key={dailyValue.displayName}>
              <td className="tg-0lax">{total}</td>
              <td className="tg-0lax">{percent}</td>
            </tr>
        );
    }
    
    return;
}

function getRemainingNutrient(n){
   return (
        <tr key={n.nutrientName}>
          <td className="tg-0lax">{n.nutrientName + " " + n.outputValue + " " + n.outputUOM}</td>
          <td className="tg-0lax"></td>
        </tr>
   ) 
}

export default NutritionFacts;

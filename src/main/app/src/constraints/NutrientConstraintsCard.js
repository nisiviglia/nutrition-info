import React from 'react';
import * as dailyValues from '../product/DailyValues'; 

const NutrientConstraintsCard = props => (
    
    <div className="nutrient-constraints-card">
            <select 
                value={props.data.code} 
                name="code" 
                onChange={(e) => props.handleUpdateConstraint(props.data.id, e)} 
            >
                {dailyValues.nutrients.concat(dailyValues.vitamins).sort(nutrientTableComparator).map(n => {
                    return (<option key={n.dbCode} value={n.dbCode}>{n.displayName}</option>);
                })}
            </select>
            <select 
                value={props.data.isInclusiveLower} 
                name="isInclusiveLower" 
                onChange={(e) => props.handleUpdateConstraint(props.data.id, e)} 
            >
                <option value={false}>Greater Than</option>
                <option value={true}>Lower Than</option>
            </select>
            <input 
                type="text" 
                value={props.data.amount} 
                onChange={(e) => props.handleUpdateConstraint(props.data.id, e)} 
                pattern="[0-9]{1,4}" 
                name="amount" 
                title="a number from 0 to 9999" 
            />
            <input 
                type="submit"  
                value="X" 
                onClick={(e) => props.handleRemoveConstraint(props.data.id, e)} 
            />
    </div>

);

function nutrientTableComparator(a, b){

    if(a.displayName.charCodeAt(0) < 64 && b.displayName.charCodeAt(0) > 64){
        return 1;
    }

    if(a.displayName.charCodeAt(0) > 64 && b.displayName.charCodeAt(0) < 64){
        return -1;
    }
    
    if(a.displayName < b.displayName){
        return -1;
    }

    if(a.displayName > b.displayName){
        return 1;
    }

    return 0;
}

export default NutrientConstraintsCard;

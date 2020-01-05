import React from 'react';

const IngredientConstraintCard = props => (

    <div className="ingredient-constraints-card">
            <select 
                value={props.data.isIncluded} 
                name="isIncluded" 
                onChange={(e) => props.handleUpdateConstraint(props.data.id, e)} 
            >
                <option value={false}>Excludes</option>
                <option value={true}>Includes</option>
            </select>
            <input 
                type="text" 
                value={props.data.name} 
                onChange={(e) => props.handleUpdateConstraint(props.data.id, e)} 
                name="name" 
                title="a number from 0 to 9999" 
            />
            <input 
                type="submit"  
                value="X" 
                onClick={(e) => props.handleRemoveConstraint(props.data.id, e)} 
            />
    </div>
);

export default IngredientConstraintCard;

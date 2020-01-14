import React from 'react';
import './IngredientConstraintCard.css';

const IngredientConstraintCard = props => (

    <div className="ingredient-constraints-card">
            <select 
                value={props.data.isIncluded || true} 
                name="isIncluded" 
                onChange={(e) => props.handleUpdateConstraint(props.data.id, e)} 
            >
                <option value={false}>Excludes</option>
                <option value={true}>Includes</option>
            </select>
            <input 
                type="text" 
                value={props.data.name || ''} 
                onChange={(e) => props.handleUpdateConstraint(props.data.id, e)} 
                name="name" 
                title="Enter an ingredient name." 
                placeholder="Ingredient Name"
            />
            <input 
                type="submit"  
                value="X" 
                onClick={(e) => props.handleRemoveConstraint(props.data.id, e)} 
                name="delete"
            />
    </div>
);

export default IngredientConstraintCard;

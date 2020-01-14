import React from 'react';
import './Constraints.css';
import NutrientConstraintsCard from './NutrientConstraintsCard'
import IngredientConstraintCard from './IngredientConstraintCard'

class Constraints extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            nutrients: [],
            ingredients: [],
            max: 5,
        };
        
        this.handleAddNutrientContraint = this.handleAddNutrientContraint.bind(this);
        this.handleRemoveNutrientConstraint = this.handleRemoveNutrientConstraint.bind(this);
        this.handleUpdateNutrientConstraint = this.handleUpdateNutrientConstraint.bind(this);
        this.handleAddIngredientConstraint = this.handleAddIngredientConstraint.bind(this);
        this.handleRemoveIngredientConstraint = this.handleRemoveIngredientConstraint.bind(this);
        this.handleUpdateIngredientConstraint = this.handleUpdateIngredientConstraint.bind(this);
    }

    componentDidMount(){

        if(this.props.constraints.nutrientConstraints != null){
            this.setState({nutrients: this.props.constraints.nutrientConstraints});
        }
        
        if(this.props.constraints.ingredientConstraints != null){
            this.setState({ingredients: this.props.constraints.ingredientConstraints});
        }
    }

    handleAddNutrientContraint(event){

        if(this.state.nutrients.length >= this.state.max){
            return;
        }

        const newID = Math.random().toString(36).substr(2,9);

        let c = { 
            id: newID,
            code: 1008,
            isInclusiveLower: true,
            amount: ""
        };
        
        let array = Array.from(this.state.nutrients);
        array.push(c);
        this.setState({nutrients: array});
        let object = {
            "nutrientConstraints": array,
            "ingredientConstraints": this.state.ingredients
        };
        this.props.handleUpdateConstraints(object);
    }

    handleAddIngredientConstraint(event){
    
        if(this.state.ingredients.length >= this.state.max){
            return;
        }

        const newID = Math.random().toString(36).substr(2,9);

        let c = {
            id: newID,
            isIncluded: true,
            text: ""
        };

        let array = Array.from(this.state.ingredients);
        array.push(c);
        this.setState({ingredients: array});
        let object = {
            "nutrientConstraints": this.state.nutrients,
            "ingredientConstraints": array 
        };
        this.props.handleUpdateConstraints(object);
    }

    handleRemoveNutrientConstraint(id, event){

        let array = Array.from(this.state.nutrients);

        for(let i=0; i < array.length; i++){
            if(id === array[i].id){
                array.splice(i, 1);
                this.setState({nutrients: array});
                let object = {
                    "nutrientConstraints": array,
                    "ingredientConstraints": this.state.ingredients
                };
                this.props.handleUpdateConstraints(object);
                return;
            }
        }
    }

    handleRemoveIngredientConstraint(id, event){
    
        let array = Array.from(this.state.ingredients);

        for(let i=0; i < array.length; i++){
            if(id === array[i].id){
                array.splice(i, 1);
                this.setState({ingredients: array});
                let object = {
                    "nutrientConstraints": this.state.nutrients,
                    "ingredientConstraints": array 
                };
                this.props.handleUpdateConstraints(object);
                return;
            }
        }
    }

    handleUpdateNutrientConstraint(id, event){

        const target = event.target; 
        const name = target.name;

        let array = Array.from(this.state.nutrients);

        for(let i=0; i < array.length; i++){

            if(id === array[i].id){
                
                if("code" === name){
                    array[i].code = Number(target.value);
                
                }
                else if("isInclusiveLower" === name){
                    array[i].isInclusiveLower = target.value;
                
                }
                else if("amount" === name){
                    array[i].amount = target.value;
                
                }

                this.setState({nutrients: array});
                let object = {
                    "nutrientConstraints": array,
                    "ingredientConstraints": this.state.ingredients
                };
                this.props.handleUpdateConstraints(object);
                return;
            }
        }
    }

    handleUpdateIngredientConstraint(id, event){

        const target = event.target; 
        const name = target.name;

        let array = Array.from(this.state.ingredients);

        for(let i=0; i < array.length; i++){

            if(id === array[i].id){
                
                if("name" === name){
                    array[i].name = target.value;
                
                }
                else if("isIncluded" === name){
                    array[i].isIncluded = target.value;
                
                }

                this.setState({ingredients: array});
                let object = {
                    "nutrientConstraints": this.state.nutrients,
                    "ingredientConstraints": array 
                };
                this.props.handleUpdateConstraints(object);
                return;
            }
        }
    
    }

    render() {
        return (
            <div className="constraints-container">
            <fieldset>
                <legend>Advanced Search</legend>
                <div className="constraints-container-buttons"> 
                    <div className="tooltip-right" data-tooltip="Add a nutrient or Ingredient to limit your search to certain foods.">
                        <button className="nutrient-button" type="button" onClick={this.handleAddNutrientContraint}>Add Nutrient</button> 
                        <button type="button" className="ingredient-button" onClick={this.handleAddIngredientConstraint}>Add Ingredient</button> 
                    </div>
                </div>
                <div className="constraints-list">
                    {this.state.nutrients.map(c => 
                        <NutrientConstraintsCard
                            key={c.id}
                            data={c}
                            handleRemoveConstraint={this.handleRemoveNutrientConstraint}
                            handleUpdateConstraint={this.handleUpdateNutrientConstraint}
                        />
                    )}
                    {this.state.ingredients.map(c =>
                        <IngredientConstraintCard
                            key={c.id}
                            data={c}
                            handleRemoveConstraint={this.handleRemoveIngredientConstraint}
                            handleUpdateConstraint={this.handleUpdateIngredientConstraint}
                        />
                    )}
                </div>
            </fieldset>
            </div>
        );
    }

}

export default Constraints;

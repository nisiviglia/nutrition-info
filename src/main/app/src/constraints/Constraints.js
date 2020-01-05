import React from 'react';
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
        this.createConstraintsObjectFromState = this.createConstraintsObjectFromState.bind(this);
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
            amount: 0
        };
        
        let array = Array.from(this.state.nutrients);
        array.push(c);
        this.setState({nutrients: array});
        this.props.handleUpdateConstraints(this.createConstraintsObjectFromState());
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
        this.props.handleUpdateConstraints(this.createConstraintsObjectFromState());
    }

    handleRemoveNutrientConstraint(id, event){

        let array = Array.from(this.state.nutrients);

        for(let i=0; i < array.length; i++){
            if(id === array[i].id){
                array.splice(i, 1);
                this.setState({nutrients: array});
                this.props.handleUpdateConstraints(this.createConstraintsObjectFromState());
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
                this.props.handleUpdateConstraints(this.createConstraintsObjectFromState());
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
                    array[i].amount = Number(target.value);
                
                }

                this.setState({nutrients: array});
                this.props.handleUpdateConstraints(this.createConstraintsObjectFromState());
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
                this.props.handleUpdateConstraints(this.createConstraintsObjectFromState());
                return;
            }
        }
    
    }

    createConstraintsObjectFromState(){
        let object = {
            "nutrientConstraints": this.state.nutrients,
            "ingredientConstraints": this.state.ingredients
        };

        return object;
    }

    render() {
        return (
            <div className="constraints-container">
                <button type="button" onClick={this.handleAddNutrientContraint}>+Nutrient</button> 
                <button type="button" onClick={this.handleAddIngredientConstraint}>+Ingredient</button> 
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
            </div>
        );
    }

}

export default Constraints;

import React from 'react';
import ConstraintsCard from './ConstraintsCard'

class Constraints extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            constraints: [],
            max: 5,
        };
        
        this.handleAddContraint = this.handleAddContraint.bind(this);
        this.handleRemoveConstraint = this.handleRemoveConstraint.bind(this);
        this.handleUpdateConstraint = this.handleUpdateConstraint.bind(this);
    }

    componentDidMount(){
        this.setState({constraints: this.props.constraints});
    }

    handleAddContraint(event){

        if(this.state.constraints.length >= this.state.max){
            return;
        }

        const newID = Math.random().toString(36).substr(2,9);

        let c = { 
            id: newID,
            code: 1008,
            isInclusiveLower: true,
            amount: 0
        };
        
        let array = Array.from(this.state.constraints);
        array.push(c);
        this.setState({constraints: array});
        this.props.handleUpdateConstraints(array);
    }

    handleRemoveConstraint(id, event){

        let array = Array.from(this.state.constraints);

        for(let i=0; i < array.length; i++){
            if(id === array[i].id){
                array.splice(i, 1);
                this.setState({constraints: array});
                this.props.handleUpdateConstraints(array);
                return;
            }
        }
    }

    handleUpdateConstraint(id, event){

        const target = event.target; 
        const name = target.name;

        let array = Array.from(this.state.constraints);

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

                this.setState({constraints: array});
                this.props.handleUpdateConstraints(array);
                return;
            }
        }
    }

    render() {
        return (
            <div className="constraints-container">
                <button type="button" onClick={this.handleAddContraint}>+Limit</button> 
                <div className="constraints-list">
                    {this.state.constraints.map(c => 
                        <ConstraintsCard
                            key={c.id}
                            data={c}
                            handleRemoveConstraint={this.handleRemoveConstraint}
                            handleUpdateConstraint={this.handleUpdateConstraint}
                        />
                    )}
                </div>
            </div>
        );
    }

}

export default Constraints;

import React from 'react';
import './ProductPage.css';
import * as api from '../api/SearchAPI.js';
import NutritionFacts from './NutritionFacts';

class ProductPage extends React.Component {
    
    constructor(props){
        super(); 
        this.state = {
            product: {}
        };

        this.handleLoadProduct(props.match.params.ndbNumber); 
    };

    handleLoadProduct(ndbNumber) {
        api.getProduct(ndbNumber)
        .then(data => {
            this.setState({product: data});
        })
        .catch(err => {
            console.log(err.message);
        })
    }

    render() {
        return (
        <div className="product-page">
            <h1 id="product_name">{this.state.product.longName}</h1>
            <p id="product_manufacturer">{this.state.product.manufacturer}</p>

            {Object.keys(this.state.product).length ? 
                <NutritionFacts product={this.state.product}/> 
            : <p>Loading...</p>}
        </div>
        ); 
    }
}

export default ProductPage;

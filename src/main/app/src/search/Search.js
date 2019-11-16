import React from 'react';
import './Search.css';
import ProductsCard from './ProductsCard';
import * as api from '../api/SearchAPI';
import Pagination from './Pagination';
import SearchStore from './SearchStore';
import Constraints from '../constraints/Constraints';

class Search extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            search_query: '',
            total_results: 0,
            max_results: 0,
            returned_results: 0,
            first_result: 0,
            products: [],
            constraints: [],
        };
        
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.sendSearchQuery = this.sendSearchQuery.bind(this);
        this.sendSearchQueryWithConstraints = this.sendSearchQueryWithConstraints.bind(this);
        this.handleUpdateConstraints = this.handleUpdateConstraints.bind(this);
        this.handleLoadMoreProducts = this.handleLoadMoreProducts.bind(this);
        this.handleKeyPress = this.handleKeyPress.bind(this);
        document.title = window.location.hostname;
    }

    componentWillMount() {
        let state = SearchStore.get();
        if(state){
            this.setState(state); 
        }
    }

    componentWillUnmount(){
        SearchStore.set(this.state);
        console.log(this.state.constraints);
    }
    
    handleChange(event) {
        this.setState({search_query: event.target.value});
    }

    handleKeyPress(e) {
        if(e.key === "Enter"){
            this.handleSubmit();
        }
    }

    handleSubmit() {

        if(this.state.search_query === ''){
            return;
        }

        if(this.state.constraints.length === 0){
            
            this.sendSearchQuery();
        } else {
        
            this.sendSearchQueryWithConstraints();
        }

    }

    sendSearchQuery(){
    
        api.searchLongName(this.state.search_query)
        .then(data => {
            this.setState({total_results: data['total_results'] 
                ? data['total_results'] : 0});
            this.setState({max_results: data['max_results'] 
                ? data['max_results'] : 0});
            this.setState({returned_results: data['returned_results'] 
                ? data['returned_results'] : 0});
            this.setState({first_result: data['first_result'] 
                ? data['first_result'] : 0});
            this.setState({products: data['products'] 
                ? data['products'] : []});
        })
        .catch(err => {
            console.log(err.message);
        })
    
    }
    
    sendSearchQueryWithConstraints(){

        api.searchLongNameWithConstraints(this.state.search_query, this.state.constraints)
        .then(data => {
            this.setState({total_results: data['total_results'] 
                ? data['total_results'] : 0});
            this.setState({max_results: data['max_results'] 
                ? data['max_results'] : 0});
            this.setState({returned_results: data['returned_results'] 
                ? data['returned_results'] : 0});
            this.setState({first_result: data['first_result'] 
                ? data['first_result'] : 0});
            this.setState({products: data['products'] 
                ? data['products'] : []});
        })
        .catch(err => {
            console.log(err.message);
        })
    
    }

    handleUpdateConstraints(array){
        this.setState({constraints: array});
    }

    handleLoadMoreProducts(event){

        if(this.state.constraints.length === 0){
            
            this.sendLoadMoreProducts();
        } else {
        
            this.sendLoadMoreProductsWithConstraints();
        }
    }

    sendLoadMoreProducts(){
    
        api.searchLongNameAfter(this.state.search_query, this.state.first_result + this.state.max_results)
        .then(data => {
            this.setState({first_result: data['first_result']});
            this.setState({products: this.state.products.concat(data['products'])});
        })
        .catch(err => {
            console.log(err.message);
        })
    }

    sendLoadMoreProductsWithConstraints(){
    
        api.searchLongNameAfterWithConstraints(this.state.search_query, 
                this.state.first_result + this.state.max_results, this.state.constraints)
        .then(data => {
            this.setState({first_result: data['first_result']});
            this.setState({products: this.state.products.concat(data['products'])});
        })
        .catch(err => {
            console.log(err.message);
        })
    }

    getNutrientValue(name, product){
        let i = product.nutrients.findIndex(n => n.nutrientName === name);
        let outputValue = 0;

        if(i !== -1){
            outputValue = product.nutrients[i].outputValue;
            outputValue = (product.servingSize / 100) * outputValue;
        }

        return outputValue.toFixed(0);
    }

    render() {
        return (
            <div className="search-container">
                <div className="searchbar" >
                    <input type="text" 
                        spellCheck="true" 
                        value={this.state.search_query} 
                        onChange={this.handleChange} 
                        onKeyPress={this.handleKeyPress}
                    />
                    <button type="button" onClick={this.handleSubmit}>
                        <img src="musica-searcher.svg" alt="Search"/>
                    </button>
                </div>

                <Constraints 
                    handleUpdateConstraints={this.handleUpdateConstraints}
                    constraints={this.state.constraints}
                />

                <div className="results-container">
                    {this.state.products.map(product => 
                         
                        <ProductsCard
                            key= {product['ndbnumber']}
                            ndbnumber= {product['ndbnumber']}
                            longName= {product["longName"]}
                            manufacturer= {product["manufacturer"]}
                            fat= {this.getNutrientValue("Total lipid (fat)", product)}
                            protien= {this.getNutrientValue("Protein", product)}
                            carb= {this.getNutrientValue("Carbohydrate, by difference", product)}
                            cal=  {this.getNutrientValue("Energy", product)}
                            servingSize= {product.servingSize}
                            servingSizeUOM= {product.servingSizeUOM}
                        />
                    )}

                </div>
                {(this.state.first_result + this.state.max_results) < this.state.total_results && 
                    <Pagination handleLoadMoreProducts= {this.handleLoadMoreProducts.bind(this)} />}
            </div>
        );
    }
}

export default Search;

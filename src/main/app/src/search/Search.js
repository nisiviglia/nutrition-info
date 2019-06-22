import React from 'react';
import './Search.css';
import ProductsCard from './ProductsCard';
import * as api from '../api/SearchAPI';
import Pagination from './Pagination';
import SearchStore from './SearchStore';

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
        };
        
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleLoadMoreProducts = this.handleLoadMoreProducts.bind(this);
    }

    componentDidMount() {
        let state = SearchStore.get();
        if(state){
            this.setState(state); 
        }
    }

    componentWillUnmount(){
        SearchStore.set(this.state);
    }
    
    handleChange(event) {
        this.setState({search_query: event.target.value});
    }

    handleSubmit(event) {
        api.searchLongName(this.state.search_query)
        .then(data => {
            this.setState({total_results: data['total_results']});
            this.setState({max_results: data['max_results']});
            this.setState({returned_results: data['returned_results']});
            this.setState({first_result: data['first_result']});
            this.setState({products: data['products']});
        })
        .catch(err => {
            console.log(err.message);
        })
    }

    handleLoadMoreProducts(event){
        api.searchLongNameAfter(this.state.search_query, this.state.first_result + this.state.max_results)
        .then(data => {
            this.setState({first_result: data['first_result']});
            this.setState({products: this.state.products.concat(data['products'])});
        })
        .catch(err => {
            console.log(err.message);
        })
    }

    render() {
        return (
            <div className="search-container">
                <div className="searchbar" >
                    <input type="text" value={this.state.search_query} onChange={this.handleChange}/>
                    <button type="button" onClick={this.handleSubmit}>Search</button>
                </div>
                <div className="results-container">
                    {this.state.products.map(product => 
                         
                        <ProductsCard
                            key= {product['ndbnumber']}
                            ndbnumber= {product['ndbnumber']}
                            longName= {product["longName"]}
                            manufacturer= {product["manufacturer"]}
                            fat= {product["nutrients"].filter(n => n["nutrientName"] === "Total lipid (fat)")[0]["outputValue"]}
                            protien= {product["nutrients"].filter(n => n["nutrientName"] === "Protein")[0]["outputValue"]}
                            sodium= {product["nutrients"].filter(n => n["nutrientName"] === "Sodium, Na")[0]["outputValue"]}
                            cal=  {product["nutrients"].filter(n => n["nutrientName"] === "Carbohydrate, by difference")[0]["outputValue"]}
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

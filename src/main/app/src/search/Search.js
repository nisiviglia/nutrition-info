import React from 'react';
import './Search.css';
import ProductsCard from './ProductsCard';

class Search extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            value: '',
            products: []
        };
        
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        let url = 'http://localhost:5000/api/v1/search/name/' + this.state.value;
        fetch(url)
        .then(resp => {
            return resp.json();
        })
        .then(data => {
            this.setState({products: data});
        })
        .catch(err => {
            console.log(err.message);
        })
    }
    
    render() {
        return (
            <div className="search-container">
                <div className="searchbar" >
                    <input type="text" value={this.state.value} onChange={this.handleChange}/>
                    <button type="button" onClick={this.handleSubmit}>Search</button>
                </div>
                <div className="results-container">
                    {this.state.products.map(product => 
                         
                        <ProductsCard
                            longName= {product["longName"]}
                            manufacturer= {product["manufacturer"]}
                            fat= {product["nutrients"].filter(n => n["nutrientName"] === "Total lipid (fat)")[0]["outputValue"]}
                            protien= {product["nutrients"].filter(n => n["nutrientName"] === "Protein")[0]["outputValue"]}
                            sodium= {product["nutrients"].filter(n => n["nutrientName"] === "Sodium, Na")[0]["outputValue"]}
                            cal=  {product["nutrients"].filter(n => n["nutrientName"] === "Carbohydrate, by difference")[0]["outputValue"]}
                        />
                    )}
                </div>
            </div>
        );
    }
}

export default Search;

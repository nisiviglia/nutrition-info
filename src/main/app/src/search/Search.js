import React from 'react';
import './Search.css';
import ProductsCard from './ProductsCard';

class Search extends React.Component {
    render() {
        
        return (
            <div class="search-container">
                <div class="searchbar">
                    <input type="text" placeholder="Search.." name="search"/>
                    <button type="submit">Submit</button> 
                </div>
                <div class="results-container">
                    <ProductsCard
                        longName="Product Name"
                        manufacter="Manufacter"
                        fat="5.0"
                        protien="15.0"
                        sodium="200.0"
                        cal="500.0"
                    />
                </div>
            </div>
        );
    }
}

export default Search;

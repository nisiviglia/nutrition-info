import React from 'react';
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
                    <ProductsCard />
                </div>
            </div>
        );
    }
}

export default Search;

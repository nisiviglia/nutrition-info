import React from 'react';
import './Pagination.css';

const Pagination = props => (
            <div className="pagination">
                <button className="pagination-button" 
                    onClick={() => props.handleLoadMoreProducts()}>Load More</button>
            </div>
);

export default Pagination;

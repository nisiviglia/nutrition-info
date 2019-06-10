import React from 'react';
import './ProductsCard.css';

const ProductsCard = props => (
    <div className="product-card">
        <div className="product-card-title">
            <h4>{props.longName}</h4>
            <p>{props.manufacturer}</p>
        </div>
        <div className="product-card-body">
            <div className="product-card-body-item">
                <p>Fat</p>
                <h4>{props.fat}</h4>
            </div>
            <div className="product-card-body-item">
                <p>Protien</p>
                <h4>{props.protien}</h4>
            </div>
            <div className="product-card-body-item">
                <p>Sodium</p>
                <h4>{props.sodium}</h4>
            </div>
            <div className="product-card-body-item">
                <p>Cal</p>
                <h4>{props.cal}</h4>
            </div>
        </div>
    </div>
);

export default ProductsCard;

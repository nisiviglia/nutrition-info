import React from 'react';
import './ProductsCard.css';
import { Link } from 'react-router-dom';
import getSlug from 'speakingurl';

const ProductsCard = props => (
    <Link 
        to={"/product/" + props.ndbnumber + "/" 
            + getSlug(props.manufacturer + " " + props.longName)} 
        className="product-card">
        <div className="product-card-title">
            <h4>{props.longName}</h4>
            <p>{props.manufacturer}</p>
        </div>
        <div className="product-card-body">
            <div className="product-card-body-item">
                <p>Fat</p>
                <h4>{props.fat} g</h4>
            </div>
            <div className="product-card-body-item">
                <p>Protien</p>
                <h4>{props.protien} g</h4>
            </div>
            <div className="product-card-body-item">
                <p>Carbs</p>
                <h4>{props.carb} g</h4>
            </div>
            <div className="product-card-body-item">
                <p>Calories</p>
                <h4>{props.cal}</h4>
            </div>
            <div className="product-card-body-item">
                <p>Serving Size</p>
                <h4>{props.servingSize} {props.servingSizeUOM}</h4>
            </div>
        </div>
    </Link>
);

export default ProductsCard;

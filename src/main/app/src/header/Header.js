import React from 'react';
import './Header.css';
import { Link } from 'react-router-dom';
import SearchStore from '../search/SearchStore';

class Header extends React.Component {
    render() {
        return (
            <div className="nav-bar">
                <ul className="nav-bar-list">
                    <li>
                        <Link 
                            to={"/"} 
                            className="nav-bar-list-home" 
                            onClick={handleHomeButton}>
                        <p>{window.location.hostname}</p>
                        </Link>
                    </li>
                </ul>
            </div>
        );
    }
}

function handleHomeButton() {
    SearchStore.set({});
}

export default Header;

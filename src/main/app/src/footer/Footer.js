import React from 'react';
import './Footer.css';
import { Link } from 'react-router-dom';

class Footer extends React.Component {
    render() {
    
        return (
            <div className="footer-container">
                <p><a href="https://github.com/nisiviglia">{'\u00A9'} 2019 Nicholas Siviglia</a></p>
                <p>{'\u2022'}</p>
                <Link to="/terms"><p>Terms</p></Link>
                <p>{'\u2022'}</p>
                <p><a href="https://www.flaticon.com/authors/egor-rumyantsev"> Icons made by: Egor Rumyantsev</a></p>
            </div>
        );
    }
}

export default Footer;

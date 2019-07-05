import React from 'react';
import './Terms.css';

const Terms = props => (
    <div className="terms-container">
        {setTitle()}
        <h2>Terms and Conditions</h2>
        <p>By accessing this website we assume you accept these terms and conditions in full. Do not continue to use this website if you do not accept all of the terms and conditions stated on this page. This website was created in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. All information found on this website is for reference only and NOT TO BE RELIED ON. Always check food labels in person before purchase and consumption.</p>
    </div>    
);

function setTitle(){
    document.title = window.location.hostname + " \u2022 Terms";
}

export default Terms;

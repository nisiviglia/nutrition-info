import React from 'react';
import { Route, Switch } from 'react-router-dom';
import './App.css';
import Header from '../header/Header';
import Footer from '../footer/Footer';
import Search from '../search/Search';
import ProductPage from '../product/ProductPage';

function App() {

  return (
    <div className="app">
        <Header />
        <Switch>
            <Route exact path="/" component={Search}/>
            <Route path="/product/:ndbNumber" component={ProductPage}/>
        </Switch>
        <Footer />
    </div>
  );
}

export default App;

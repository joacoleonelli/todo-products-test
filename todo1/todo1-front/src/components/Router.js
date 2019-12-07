import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import App from '../App';
import Product from './Product';

const Router = () => (
    <BrowserRouter>
        <Switch>
            <Route exact path="/" component={App} />
            <Route path="/inicio" component={App} />
            <Route path="/products" component={Product} />
        </Switch>
    </BrowserRouter>
)

export default Router;
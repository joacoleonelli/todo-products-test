import React, { Component } from 'react';
import './App.css';
import Menu from './components/Menu';

class App extends Component {
  render() {
    return (
      <Menu history={this.props.history}/>
    );
  }
}

export default App;

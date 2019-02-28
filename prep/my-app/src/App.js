//@flow
import React, { Component } from 'react';
import './App.css';

import * as api from 'demo'

class App extends Component {
    
  constructor(props) {
      super(props);

      this.state = {
          dogs: []
      };

      api.DefaultApi().dogsGet()
          .then(dogs => {
              this.setState({dogs})
          })
  }

  render() {
      const { dogs } = this.state;
      return (
        <div className={App}>
          <ul>
          {dogs.length === 0 ? ("No Dogs!") : dogs.map(dog =>(
              <li key={dog.id}>{dog.name}</li>
          ))}
          </ul>
        </div>
      );
  }
}

export default App;

-------------
# Introduction
The OpenAPI Specification (OAS) defines a standard, programming language-agnostic interface description for REST APIs, which allows both humans and computers to discover and understand the capabilities of a service without requiring access to source code, additional documentation, or inspection of network traffic. When properly defined via OpenAPI, a consumer can understand and interact with the remote service with a minimal amount of implementation logic. Similar to what interface descriptions have done for lower-level programming, the OpenAPI Specification removes guesswork in calling a service.

Use cases for machine-readable API definition documents include, but are not limited to: interactive documentation; code generation for documentation, clients, and servers; and automation of test cases. OpenAPI documents describe an API's services and are represented in either YAML or JSON formats. These documents may either be produced and served statically or be generated dynamically from an application.

The OpenAPI Specification does not require rewriting existing APIs. It does not require binding any software to a service — the service being described may not even be owned by the creator of its description. It does, however, require the capabilities of the service be described in the structure of the OpenAPI Specification. Not all services can be described by OpenAPI — this specification is not intended to cover every possible style of REST APIs. The OpenAPI Specification does not mandate a specific development process such as design-first or code-first. It does facilitate either technique by establishing clear interactions with a REST API.

https://github.com/OAI/OpenAPI-Specification

## Content

### __Folders:__
- __prep__: in this folder i stored the prepared code from the generator with all changes i made to it (described more down)

- __example-001__:
absolut minimum of required fields

- __example-002__:
some documentation sugger and full CRUD

## Validate
```bash
java -jar openapi-generator-cli-3.3.4.jar validate -i example-002/openapi.yaml
```


## Generate documentation
```bash
docker run -p 81:8080 -e URL=specs/openapi.yaml \
-v ${PWD}/example-002:/usr/share/nginx/html/specs \
swaggerapi/swagger-ui
```

## Generate server

```bash
java -jar openapi-generator-cli-3.3.4.jar generate -i example-002/openapi.yaml -g spring -o out/server

cd out/server
mvn package
```
! Enable (uncomment) CORS in `out/server/src/main/java/com/example/demo/OpenAPI2SpringBoot.java`

http://localhost:8181/swagger-ui.html




## Create demo app
```bash
cd out
yarn create-react-app my-app
cd my-app
yarn start
```


## Create client lib

### Create the client code
```bash
java -jar openapi-generator-cli-3.3.4.jar generate -i example-002/openapi.yaml -g javascript-flowtyped -o out/client
```

### Build it
```bash
cd out/client
yarn
yarn build
```

### And install the lib
```bash
cd out/my-app
yarn add ../client
```
When server is running, the Frontend team can already use the API as a mock.



# Server response

Make some adjustments to the Server so we can show some stuff

File: `out/server/src/main/java/com/example/demo/api/DogsApiController.java`
```java
package com.example.demo.api;

import com.example.demo.model.Dog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class DogsApiController implements DogsApi {

    @Override
    public ResponseEntity<List<Dog>> dogsGet() {
      List<Dog> dogs = new ArrayList<>();

        Dog dogOne = new Dog();
        dogOne.setId("123");
        dogOne.setName("willy");
        dogOne.setAge(10);
        dogOne.setCountry("DE");

        Dog dogTwo = new Dog();
        dogTwo.setId("202");
        dogTwo.setName("bobo");

        dogs.add(dogOne);
        dogs.add(dogTwo);


        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }
}
```


# Add client
Add some client code so we show the response from the server

File: `out/my-app/src/App.js`
```jsx
//@flow
import React, { Component } from 'react';
import './App.css';

import * as api from 'demo'

class App extends Component {
    
  constructor() {
      super();

      this.state.dogs = [];
      
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

```
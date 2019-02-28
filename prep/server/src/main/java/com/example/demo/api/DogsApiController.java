package com.example.demo.api;

import com.example.demo.model.Dog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
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

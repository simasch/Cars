package com.example.car;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cars")
public class CarController {

    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Color> colors = new ArrayList<>();


    @GetMapping
    public ArrayList<Car> getCars() {
        return cars;
    }

    @PostMapping
    public ResponseEntity<String> addCar(@RequestBody Car car) {

        if (!checkInput(car)) {
            return ResponseEntity.status(400).body("Please send all the fields");
        }
        cars.add(car);
        return ResponseEntity.status(200).body("Car created");
    }

    @PostMapping("/color")
    public ResponseEntity colorFunds(@RequestBody Color color){

        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getColor().equals(color.getColor())) {
                if(!(cars.get(i).getColor().equals(color.getColor()))){
                    return ResponseEntity.status(400).body("color doesn't match the user id");
                }
                return ResponseEntity.status(200).body("Color found");
            }
        }
        return ResponseEntity.status(400).body("wrong id");
    }

    public boolean checkInput(Car car){
        if(car.getId()==0||
                car.getColor()==null
                || car.getModel() ==null
                || car.getYearOfMake() ==0
                || car.getPrice()==0){
            return false;
        }
        return  true;
    }
}

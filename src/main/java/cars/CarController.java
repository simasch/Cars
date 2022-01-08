package cars;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/cars")
public class CarController {

    // Use a set to guarantee uniqueness
    private Set<Car> cars = new HashSet<>();

    @GetMapping
    public Set<Car> getCars() {
        return cars;
    }

    @PostMapping
    public ResponseEntity<String> addCar(@RequestBody Car car) {
        if (!checkInput(car)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send all the fields");
        } else {
            cars.add(car);
            return ResponseEntity.status(HttpStatus.CREATED).body("Car created");
        }
    }

    @GetMapping("/color")
    public ResponseEntity<Car> colorFunds(@RequestParam String color) {
        return ResponseEntity.of(cars.stream().filter(car -> car.getColor().equals(color)).findFirst());
    }

    public boolean checkInput(Car car) {
        return car.getId() != 0
                && car.getColor() != null
                && car.getModel() != null
                && car.getYearOfMake() != 0
                && car.getPrice() != 0;
    }
}

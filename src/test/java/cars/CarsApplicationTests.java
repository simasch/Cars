package cars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarsApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void addAndGetAndFindByColor() {
        // Create a car
        Car car = new Car(1, "Ford Edge", "black", 70000, 2015);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/cars", car, String.class);
        assertEquals("Car created", response.getBody());

        // Get all cars
        ResponseEntity<Car[]> cars = restTemplate.getForEntity("http://localhost:" + port + "/cars", Car[].class);
        assertEquals(1, cars.getBody().length);

        // Get red cars
        ResponseEntity<Object> redCar = restTemplate.getForEntity("http://localhost:" + port + "/cars/color?color=red", Object.class);
        assertNull(redCar.getBody());

        // Get black cars
        ResponseEntity<Car> blackCar = restTemplate.getForEntity("http://localhost:" + port + "/cars/color?color=black", Car.class);
        assertNotNull(blackCar.getBody());

    }

}

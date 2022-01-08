package cars;

import java.util.Objects;

public class Car {

    private int id;
    private String model;
    private String color;
    private int price;
    private int yearOfMake;

    public Car(int id, String model, String color, int price, int yearOfMake) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.price = price;
        this.yearOfMake = yearOfMake;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYearOfMake() {
        return yearOfMake;
    }

    public void setYearOfMake(int yearOfMake) {
        this.yearOfMake = yearOfMake;
    }

    /*
     * Implement equals and hashCode to guarantee uniqueness
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

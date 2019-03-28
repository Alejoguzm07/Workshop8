package edu.eci.models;

import java.io.Serializable;

public class Car implements Serializable {

    private String licencePlate;
    private String brand;

    public Car() {
    }

    public Car(String licencePlate, String brand) {
        this.licencePlate = licencePlate;
        this.brand = brand;
    }

    public String getlicencePlate() {
        return licencePlate;
    }

    public void setlicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getbrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

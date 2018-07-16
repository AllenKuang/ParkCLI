package com.thoughtworks.tdd;

public class Car {
    private String brand;

    Car(){

    }
    Car(String brand){
        this.brand=brand;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

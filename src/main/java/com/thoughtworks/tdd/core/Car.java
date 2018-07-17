package com.thoughtworks.tdd.core;

public class Car {

        private String brand;

        public Car(){

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

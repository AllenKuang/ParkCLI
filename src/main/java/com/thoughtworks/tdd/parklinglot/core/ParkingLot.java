package com.thoughtworks.tdd.parklinglot.core;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private String id;
    private String name;
    private int size;
    private int stoppedCar;
    private Map<Receipt, Car> parkedCars = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }
    public ParkingLot(String id, String name, int size, int stoppedCar) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.stoppedCar = stoppedCar;
    }
    public Receipt park(Car car) {
        if (parkedCars.size() == this.size) {
            throw new ParkingLotFullException();
        }

        Receipt key = new Receipt();
        this.parkedCars.put(key, car);
        return key;
    }

    public boolean containCar(Receipt parkingTicket) {
        return this.parkedCars.containsKey(parkingTicket);
    }


    public Car unPark(Receipt receipt) {
        return this.parkedCars.remove(receipt);
    }

    public boolean isFull() {
        return this.parkedCars.size() == size;
    }

    public int getSize() {
        return this.size;
    }

    public int getstoppedCar() {
        return this.stoppedCar;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}

package com.thoughtworks.tdd.parklinglot.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ParkingBoy  {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = new ArrayList<ParkingLot>(Arrays.asList(parkingLots));
    }

    public Receipt park(Car car) throws ParkingLotFullException {

        ParkingLot currentParkingLot = null;
        try {
            currentParkingLot = parkingLots.stream()
                    .filter((ParkingLot parkingLot) -> !parkingLot.isFull())
                    .findFirst().get();
        }catch(NoSuchElementException e){
            throw new ParkingLotFullException(e);
        }
        return currentParkingLot.park(car);
    }

    public boolean isFull() {
        return parkingLots.stream().allMatch((ParkingLot parkingLot) -> parkingLot.isFull());
    }

    public Car getCar(Receipt parkingTicket) {
        ParkingLot currentParkingLot = null;
        try {
            currentParkingLot = parkingLots.stream()
                    .filter((ParkingLot parkingLot) -> parkingLot.containCar(parkingTicket))
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
        return currentParkingLot.unPark(parkingTicket);
    }

    public List<ParkingLot> getParkingLots(){
        return this.parkingLots;
    }

    public void  addParkingLot(ParkingLot parkingLot){
        this.parkingLots.add(parkingLot);
    }
}

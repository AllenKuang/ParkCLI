package com.thoughtworks.tdd;

import java.util.BitSet;
import java.util.HashMap;

public class ParkingLot {
    private int size;
    private HashMap<Receipt,Car> parkcars;
    public ParkingLot(int capality) {
        this.size=capality;
        parkcars=new HashMap<>();
    }

    public boolean isFull() {
     return this.size==0;
    }

    public Receipt park(Car car) {
        if(isFull()){
            throw new ParkingLotFullException();
        }
        else {
            this.size--;
            Receipt receipt=new Receipt();
            this.parkcars.put(receipt,car);
            return receipt;
        }

    }

    public Car unPark(Receipt receipt) {
        if(this.parkcars.containsKey(receipt)){
            this.size++;
            return  parkcars.remove(receipt);
        }else{
            return null;
        }
        //return this.parkcars.get(receipt);
    }

    public int getSize() {
        return this.size;
    }

    public HashMap<Receipt,Car> getparkcars() {
        return this.parkcars;
    }
}

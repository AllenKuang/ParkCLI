package com.thoughtworks.tdd;


import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingBoyTest {
    @Test
    public void should_park_successfully_when_given_park_not_full(){

        ParkingLot parkingLot1=mock(ParkingLot.class);

        when(parkingLot1.isFull()).thenReturn(false);

        ArrayList<ParkingLot> parkingLots=new ArrayList<>();

        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy =new ParkingBoy(parkingLots);

        Car car =new Car();
        try {
            parkingBoy.park(car);
            verify(parkingLot1).park(car);
        }catch(ParkingLotFullException exception){
            fail("should park successfully");
        }
        //assertThat(parksuccess, is(true));
    }

    @Test
    public void should_park_successfully_when_given_park_not_full_without_exception(){

        ParkingLot parkingLot1=new ParkingLot(1);

        ArrayList<ParkingLot> parkingLots=new ArrayList<>();

        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy =new ParkingBoy(parkingLots);

        Car car =new Car();
        Receipt receipt =parkingBoy.park(car);
        ParkingLot lotParkMyCar=parkingBoy.getparkingLots().get(0);

        assertTrue(lotParkMyCar.getparkcars().containsKey(receipt));
        //assertThat(parksuccess, is(true));
    }

    @Test
    public void should_park_failed_when_given_park_is_full(){

        ParkingLot parkingLot1=mock(ParkingLot.class);

        when(parkingLot1.isFull()).thenReturn(true);

        //ParkingLot parkingLot1=new ParkingLot(0);

        ArrayList<ParkingLot> parkingLots=new ArrayList<>();

        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy =new ParkingBoy(parkingLots);

        Car car =new Car();

        try {
            parkingBoy.park(car);
            fail("should park successfully");
        }catch(ParkingLotFullException exception){

        }

//        Boolean parksuccess= parkingBoy.park(car);
//
//        assertThat(parksuccess, is(false));
    }

    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        //ParkingLot parkingLot1 =mock(ParkingLot.class);


        ArrayList<ParkingLot> parkingLots=new ArrayList<>();

        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy =new ParkingBoy(parkingLots);
        Car theCar = new Car();

//        when(parkingLot1.park(theCar)).thenReturn(parkingBoy.park(theCar));

        Receipt receipt=parkingBoy.park(theCar);
        //assertThat(parkingBoy.unPark(receipt),is(theCar));
        assertEquals(theCar,parkingBoy.unPark(receipt));
    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();

        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy =new ParkingBoy(parkingLots);
        Car theCar = new Car();

        parkingBoy.park(theCar);

        Receipt anotherReceipt = new Receipt();
        assertThat(parkingBoy.unPark(anotherReceipt),not(theCar));
    }

    @Test
    public void should_parking_be_order_when_a_parkingLot_isFull_(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();

        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy =new ParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Receipt receipt1=parkingBoy.park(firstCar);
        Receipt receipt2=parkingBoy.park(secondCar);
        //parkingBoy.unPark(receipt1);

        assertThat(firstCar,is(parkingBoy.unPark(receipt1)));
        //assertThat(parkingLot2.unPark(receipt2),is(parkingBoy.unPark(receipt2)));//parkingLot2的取车操作已经将第二次停的车取出，导致后面parkingBoy取不到车，这是错误的操作，也没注意到parkingBoy用的是parkingLot对象来存储
        assertEquals(secondCar,parkingBoy.unPark(receipt2));
    }

}

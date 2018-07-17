package com.thoughtworks.tdd;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.core.ParkingLotFullException;
import com.thoughtworks.tdd.core.Receipt;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkingLotTest {
    @Test
    public void should_park_successfully_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);

        try {
            parkingLot.park(new Car.Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }

    @Test
    public void should_park_successfully_given_parking_lot_is_not_full_do_not_use_exception() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car.Car car =new Car.Car();
        Receipt receipt=parkingLot.park(car);

        //assertThat(parkingLot.getparkcars().containsKey(receipt)).is(true);
        assertTrue(parkingLot.getparkcars().containsKey(receipt));
    }

    @Test
    public void should_park_failed_given_parking_lot_is__full() {
        ParkingLot parkingLot = new ParkingLot(0);

        try {
            parkingLot.park(new Car.Car());         //出错，抛出异常直接跳到catch
            fail("should park successfully");   //不出错执行下一行，进入fail
        } catch (ParkingLotFullException exception) {

        }

    }
    @Test
    public void should_park_failed_given_parking_lot_is__full_do_not_use_exception() {
        ParkingLot parkingLot = new ParkingLot(0);

        Car.Car car =new Car.Car();
//        try {
//            parkingLot.park(car);//抛出异常，导致程序无法继续运行
//        }catch (ParkingLotFullException exception){
//            //fail("无法停车，获得异常");
//            assertFalse(parkingLot.getparkcars().containsValue(car));
//        }
        parkingLot.park(car);
    }

    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car.Car theCar = new Car.Car();
        Receipt receipt = parkingLot.park(theCar);

        assertThat(parkingLot.unPark(receipt), is(theCar));

    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car.Car theCar = new Car.Car();
        Receipt receipt = parkingLot.park(theCar);

        Receipt anotherReceipt = new Receipt();

        assertThat(parkingLot.unPark(anotherReceipt), not(theCar));
    }


    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);

        assertThat(parkingLot.isFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(1);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car.Car theCar = new Car.Car();
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_park_successfullly_when_call_park_again_given_a_full_parking_lot_take_out_a_car(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car.Car theCar = new Car.Car();
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        try {
            parkingLot.park(new Car.Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }
}

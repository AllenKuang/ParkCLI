package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParkingBoy {
    private ArrayList<ParkingLot> parkingLots;
    private HashMap<String,String> record;


    ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
    public ParkingLot findFirstNotNullparkingLot(){
        ParkingLot firstNotNullparkingLot=null;
        for (ParkingLot parkingLot : parkingLots) {
            if(!parkingLot.isFull()){
                firstNotNullparkingLot=parkingLot;
                if(firstNotNullparkingLot!=null){
                    break;
                }
            }
        }
        return firstNotNullparkingLot;

    }
    public Receipt park(Car car) {
        Receipt receipt = null;
        ParkingLot firstNotNullparkingLot=findFirstNotNullparkingLot();
        if(firstNotNullparkingLot!=null){
            receipt = findFirstNotNullparkingLot().park(car);
            return receipt;
        }
        else {
            throw new ParkingLotFullException();
        }
    }
    public void park() {
        int remainSpace =0;
        for(ParkingLot parkingLot:parkingLots){
            remainSpace+=parkingLot.getSize();
        }
        if(remainSpace==0){
            System.out.println("车已停满，请晚点再来");
        }else{
            System.out.println("请输入车牌号:");
            Scanner in= new Scanner(System.in);
            String carId=in.next();
            Receipt receipt=new Receipt();
            record.put(receipt.getId(),carId);
            System.out.println("停车成功，您的小票是：\n" +
                    receipt.getId());
        }

    }
//    public Receipt park(Car car) {
//        int wholesize = 0;
//        Receipt receipt = null;
//        for (ParkingLot parkingLot : parkingLots) {
//            wholesize = parkingLot.getSize();
//        }
//        if (wholesize != 0) {
//            ParkingLot firstNotNullparkingLot;
//            for (ParkingLot parkingLot : parkingLots) {
//                if (!parkingLot.isFull()) {
//                    firstNotNullparkingLot = parkingLot;
//                    receipt = firstNotNullparkingLot.park(car);
//                    break;
//                }
//            }
//            //return true;
//        } else {
//            throw new ParkingLotFullException();
//        }
//        return receipt;
//        //return false;
//    }

//    public Car unPark(Receipt receipt) {
//        if (this.parkingLots.get(0).getparkcars().remove(receipt) != null) {
////            Car getcar=null;
////            for (ParkingLot parkingLot : parkingLots) {
////                if(parkingLot.getparkcars().remove(receipt)!=null){
////                    getcar =parkingLot.getparkcars().remove(receipt);
////                }else{
////                    getcar=parkingLot.getparkcars().remove(receipt);
////                }
////
////            }
////            return getcar;
//            //通过小票把停车场的车取出来，一个停车场，一辆车
//            return this.parkingLots.get(0).getparkcars().remove(receipt);
//        } else {
//            return this.parkingLots.get(0).getparkcars().remove(receipt);
//        }
//
//    }

    public Car unPark(Receipt receipt) {
        Car car = null;
        for (ParkingLot parkingLot : parkingLots) {
            car = parkingLot.unPark(receipt);
            if(car!=null)break;
        }
        return car;
    }


    public ArrayList<ParkingLot> getparkingLots() {
        return this.parkingLots;
    }
}

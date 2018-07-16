package com.thoughtworks.tdd;


import com.thoughtworks.tdd.IO.Input;
import com.thoughtworks.tdd.IO.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParkingApp {
    static ParkingBoy parkingBoy=null;
    public static void main(String[] args) throws Exception {
         setParkingBoy();
         Request request=new Request();
         View view =new View();
         ParkingLotController parkingLotController=new ParkingLotController(parkingBoy,view);

         Router router=new Router(parkingLotController);
         String currentPage = "main";
         while(true){
             view.showBeginView();
             String tip=Input.input();
             request.setCommond(tip);

             //choose(tip);
             //parkingLotController.choose(tip);
             router.choosePage(currentPage,tip);
         }
    }

    public static void choose(String input) throws IOException {
        if(input.equals("1")){
            park();
        }else if(input.equals("2")){
            unpark();
        }else{
            View.showIllegalInstructionView();
        }
    }


    public static void unpark() throws IOException {
        View.showEnterReceiptIdView();
        Scanner in= new Scanner(System.in);
        ArrayList<Receipt> receipts=new ArrayList<>();

        for(ParkingLot parkingLot:parkingBoy.getparkingLots()){
            receipts.addAll(parkingLot.getparkcars().keySet());
        }
        String receipid=in.next();
        Receipt whichReceipt=null;
        for(Receipt receipt:receipts){
            if(receipid.equals(receipt.getId())){
                whichReceipt=receipt;
                break;
            }
        }
        if(whichReceipt==null){
            View.showIllegalReceiptIdView();
        }else {
            Car car = parkingBoy.unPark(whichReceipt);
            View.showBrandView(car.getBrand());
        }
    }

    public static void park() throws IOException {
        int remainSpace =0;
        for(ParkingLot parkingLot:parkingBoy.getparkingLots()){
            remainSpace+=parkingLot.getSize();
        }
        if(remainSpace==0){
            View.showisFullView();
        }else {
            View.showEnterBrandView();
            Scanner in = new Scanner(System.in);
            Car car = new Car();
            car.setBrand(in.next());
            Receipt receipt = parkingBoy.park(car);
            View.showReceiptIdView(receipt.getId());
        }
    }

    public static void setParkingBoy(){
        ParkingLot parkingLot1=new ParkingLot(1);
        ParkingLot parkingLot2=new ParkingLot(0);
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingBoy=new ParkingBoy(parkingLots);
    }
}

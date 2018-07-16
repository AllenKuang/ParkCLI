package com.thoughtworks.tdd;

import com.thoughtworks.tdd.IO.Input;
import com.thoughtworks.tdd.IO.View;

import java.io.IOException;
import java.util.ArrayList;

public class ParkingLotController {
    private ParkingBoy parkingBoy;
    private View view;
    ParkingLotController(ParkingBoy parkingBoy,View view){
        this.parkingBoy=parkingBoy;
        this.view=view;
    }
    public  String choose(String input) throws IOException {
        if(input.equals("1")){
            park();
            return "park";
        }else if(input.equals("2")){
            unpark();
            return "unpark";
        }else{
            view.showIllegalInstructionView();
            return "main";
        }
    }

    public  String park() throws IOException {
        int remainSpace =0;
        for(ParkingLot parkingLot:parkingBoy.getparkingLots()){
            remainSpace+=parkingLot.getSize();
        }
        if(remainSpace==0){
            view.showisFullView();
            return "main";
        }else {
            view.showEnterBrandView();
            Car car = new Car();
            car.setBrand(Input.input());
            Receipt receipt = parkingBoy.park(car);
            view.showReceiptIdView(receipt.getId());
            return "main";
        }
    }

    public  String unpark() throws IOException {
        view.showEnterReceiptIdView();
        ArrayList<Receipt> receipts=new ArrayList<>();

        for(ParkingLot parkingLot:parkingBoy.getparkingLots()){
            receipts.addAll(parkingLot.getparkcars().keySet());
        }
        String receipid=Input.input();
        Receipt whichReceipt=null;
        for(Receipt receipt:receipts){
            if(receipid.equals(receipt.getId())){
                whichReceipt=receipt;
                break;
            }
        }
        if(whichReceipt==null){
            view.showIllegalReceiptIdView();
            return "main";
        }else {
            Car car = parkingBoy.unPark(whichReceipt);
            view.showBrandView(car.getBrand());
            return "main";
        }
    }

}

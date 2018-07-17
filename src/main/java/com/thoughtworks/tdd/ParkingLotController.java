package com.thoughtworks.tdd;

import com.thoughtworks.tdd.IO.View;
import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.shell.io.Request;

import java.io.IOException;
import java.util.ArrayList;

public class ParkingLotController {
    private ParkingBoy parkingBoy;
    private View view;
    ParkingLotController(ParkingBoy parkingBoy,View view){
        this.parkingBoy=parkingBoy;
        this.view=view;
    }

    public String choose(Request request) throws IOException {
        if (request.getCommond().equals("1")) {
            int remainSpace = 0;
            for (ParkingLot parkingLot : parkingBoy.getparkingLots()) {
                remainSpace += parkingLot.getSize();
            }
            if (remainSpace == 0) {
                view.showisFullView();
                //view.showBeginView();
                view.showBeginViewParkManage();     //在跳出循环前直接打印根页面
                return "manageMain";
            } else {
                view.showEnterBrandView();
                return "park";
            }
        } else if (request.getCommond().equals("2")) {
            view.showEnterReceiptIdView();
            return "unpark";
        } else {
            view.showIllegalInstructionView();
            //view.showBeginView();
            view.showBeginViewParkManage();
            return "manageMain";
        }
    }

    public  String park(Request request) throws IOException {
            Car car = new Car();
            car.setBrand(request.getCommond());
            Receipt receipt = parkingBoy.park(car);
            view.showReceiptIdView(receipt.getId());
            //view.showBeginView();
            view.showBeginViewParkManage();
            return "manageMain";
    }

    public  String unpark(Request request) throws IOException {
        ArrayList<Receipt> receipts=new ArrayList<>();
        for(ParkingLot parkingLot:parkingBoy.getparkingLots()){
            receipts.addAll(parkingLot.getparkcars().keySet());
        }
        String receipid=request.getCommond();
        Receipt whichReceipt=null;
        for(Receipt receipt:receipts){
            if(receipid.equals(receipt.getId())){
                whichReceipt=receipt;
                break;
            }
        }
        if(whichReceipt==null){
            view.showIllegalReceiptIdView();
        }else {
            Car car = parkingBoy.unPark(whichReceipt);
            view.showBrandView(car.getBrand());
        }
        //view.showBeginView();
        view.showBeginViewParkManage();
        return "manageMain";
    }

}

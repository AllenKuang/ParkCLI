package com.thoughtworks.tdd;


import com.thoughtworks.tdd.IO.Input;
import com.thoughtworks.tdd.IO.View;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.shell.Router;
import com.thoughtworks.tdd.shell.io.Request;

import java.io.IOException;
import java.util.ArrayList;

public class ParkingApp {
    static ParkingBoy parkingBoy=null;
    public static void main(String[] args) throws Exception {
        setParkingBoy();
        View view =new View();
        ParkingLotController parkingLotController=new ParkingLotController(parkingBoy,view);
        Input input=new Input();
        Request request=new Request();
        Router router=new Router(parkingLotController);
        String currentPage = "main";
        while(true){
            String command=input.input();
            request.setCommond(command);
            currentPage=router.choosePage(currentPage,request);
        }
    }

    public static void call(ParkingLotManageController parkingLotManageController) throws IOException{
        setParkingBoy();
        View view =new View();
        ParkingLotController parkingLotController=new ParkingLotController(parkingBoy,view);
        Input input=new Input();
        Request request=new Request();
        Router router=new Router(parkingLotController);
        String currentPage = "main";
        while(true){
            String command=input.input();
            request.setCommond(command);
            currentPage=router.choosePage(currentPage,request,parkingLotManageController);
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

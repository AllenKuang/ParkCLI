package com.thoughtworks.tdd.parklinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.ParkingLot;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

public class AddParkLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;
    public AddParkLotController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }
    @Override
    public String process() {
        String input=this.request.getCommand();
        String[] inputArray=input.split(",");
        int size=Integer.parseInt(inputArray[1]);
        int parkingLotsNumber=this.parkingBoy.getParkingLots().size();
        parkingLotsNumber++;
        ParkingLot parkingLot = new ParkingLot("00"+parkingLotsNumber,inputArray[0],size,0);
        this.parkingBoy.addParkingLot(parkingLot);
        response.send("添加停车场成功\n");
        return "root";
    }
}
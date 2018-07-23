package com.thoughtworks.tdd.parklinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.ParkingLot;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

public class GotoCheckParkLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;
    public GotoCheckParkLotController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }
    @Override
    public String process() {
        response.send("停车场详情\n"+
                generateParkingDetails() );
        return "root";
    }
    public String generateParkingDetails() {
        int remianSize = 0;
        int allhasCarSize = 0;

        String parkingDetails = "|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n";
        for (ParkingLot parkingLot : parkingBoy.getParkingLots()) {
            int subtotalSize = parkingLot.getSize() + parkingLot.getstoppedCar();
            parkingDetails += "|" + parkingLot.getId() + "|" + parkingLot.getName() + "|" + subtotalSize + "(个)|" + parkingLot.getstoppedCar() + "(辆)|" + parkingLot.getSize() + "(个)|\n";
            remianSize += parkingLot.getSize();
            allhasCarSize += parkingLot.getstoppedCar();
        }
        int totalSize = remianSize + allhasCarSize;
        parkingDetails += "\n" +
                "总车位：" + totalSize + "(个)\n" +
                "停车总量：" + allhasCarSize + "（辆）\n" +
                "总剩余车位：" + remianSize + "（个）";
        return parkingDetails;
    }
}

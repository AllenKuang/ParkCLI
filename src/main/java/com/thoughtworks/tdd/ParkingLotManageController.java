package com.thoughtworks.tdd;

import com.thoughtworks.tdd.IO.ManageView;
import com.thoughtworks.tdd.shell.io.Request;

import java.io.IOException;

public class ParkingLotManageController {
    private ManageView manageView;
    private ParkingApp parkingApp=new ParkingApp();
    ParkingLotManageController(ManageView manageView){
        this.manageView=manageView;
    }

    public  String choose(Request request ) throws IOException {
            if (request.getCommond().equals("1")) {
                System.out.println("停车服务");
                parkingApp.call(this);
                manageView.showBeginViewParkManage();
                return "manageMain";
            } else if (request.getCommond().equals("2")) {
                System.out.println("停车场服务");
                manageView.showBeginViewParkManage();
                return "manageMain";
            } else {
                manageView.showIllegalInstructionView();
                manageView.showBeginViewParkManage();
                return "manageMain";
            }

    }
}

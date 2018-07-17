package com.thoughtworks.tdd;

import com.thoughtworks.tdd.IO.Input;
import com.thoughtworks.tdd.IO.ManageView;
import com.thoughtworks.tdd.shell.io.Request;

import java.io.IOException;

public class ParkingLotManage {
    public static void main(String[] args) throws IOException {
        ManageView manageView=new ManageView();
        ParkingLotManageController parkingLotManageController=new ParkingLotManageController(manageView);
        Input input=new Input();
        Request request=new Request();

        Boolean ismanageMain=true;
        while(true){
            String command=input.input();
            request.setCommond(command);
            parkingLotManageController.choose(request);
        }
    }
}

package com.thoughtworks.tdd.shell.controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

public class MainController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public MainController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }
    @Override
    public String process() {
        response.send("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
        return "";
    }
}

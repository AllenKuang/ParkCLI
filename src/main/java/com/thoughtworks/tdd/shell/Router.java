package com.thoughtworks.tdd.shell;

import com.thoughtworks.tdd.ParkingLotController;
import com.thoughtworks.tdd.ParkingLotManageController;
import com.thoughtworks.tdd.shell.io.Request;

import java.io.IOException;

public class Router {
    private ParkingLotController parkingLotController;
    private String currentPage;
    public Router(ParkingLotController parkingLotController) {

        this.parkingLotController = parkingLotController;
    }

    public String choosePage(String currentPage, Request request) throws IOException {
        switch (currentPage) {
            case "main":
                this.currentPage = parkingLotController.choose(request);
                break;
            case "park":
                this.currentPage = parkingLotController.park(request);
                break;
            case "unpark":
                this.currentPage = parkingLotController.unpark(request);
                break;
        }
        return this.currentPage;
    }

    public String choosePage(String currentPage, Request request,ParkingLotManageController parkingLotManageController) throws IOException {
        switch (currentPage) {
            case "main":
                this.currentPage = parkingLotController.choose(request);
                break;
            case "park":
                this.currentPage = parkingLotController.park(request);
                break;
            case "unpark":
                this.currentPage = parkingLotController.unpark(request);
                break;
            case "manageMain":
                this.currentPage=parkingLotManageController.choose(request);
                break;
        }
        return this.currentPage;
    }

}

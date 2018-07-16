package com.thoughtworks.tdd;

import java.io.IOException;

public class Router {
    private ParkingLotController parkingLotController;
    private String currentPage;
    public Router(ParkingLotController parkingLotController) {

        this.parkingLotController = parkingLotController;
    }

    public String choosePage(String currentPage, String input) throws IOException {
        switch (currentPage) {
            case "main":
                this.currentPage = parkingLotController.choose(input);
                break;
            case "park":
                this.currentPage = parkingLotController.park();
                break;
            case "unpark":
                this.currentPage = parkingLotController.unpark();
                break;
        }
        return this.currentPage;
    }
}

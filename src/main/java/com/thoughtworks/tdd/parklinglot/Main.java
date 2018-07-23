package com.thoughtworks.tdd.parklinglot;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.ParkingLot;
import com.thoughtworks.tdd.parklinglot.shell.controller.*;
import com.thoughtworks.tdd.parklinglot.shell.Router;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

import java.util.Scanner;

public class Main {

    public static Scanner cliInput = new Scanner(System.in);

    public static void main(String[] args) {

        String initRootPath = "root";
        Request request = new Request();
        Router router = initRouter(initRootPath, request);
        router.launch();

        try {
            while (true) {
                String command = cliInput.nextLine();
                request.setCommand(command);
                router.processRequest(request);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("\n App Exist, cause from route not exist!");
        } finally {
            cliInput.close();
        }
    }

    private static Router initRouter(String currentPage, Request request) {
        ParkingBoy boy = new ParkingBoy(new ParkingLot("001","西南停车场",20,8), new ParkingLot("002","西南停车场",4,8));
        Response response = new Response();

        Router router = new Router(currentPage);
        router.registerRoute("main", new MainController(request, response, boy));
        router.registerRoute("main/1", new GotoParkingController(request, response, boy));
        router.registerRoute("main/2", new GoToPickUpController(request, response, boy));
        router.registerRoute("main/1/*", new ParkingController(request, response, boy));
        router.registerRoute("main/2/*", new PickUpController(request, response, boy));

        //router.registerRoute("main/*", new RootController(request, response, boy));
        router.registerRoute("root", new RootController(request, response, boy));

        router.registerRoute("manage", new ManageParkLotController(request, response, boy));
        router.registerRoute("manage/1", new GotoCheckParkLotController(request, response, boy));
        router.registerRoute("manage/2", new GotoAddParkLotController(request, response, boy));
        //router.registerRoute("manage/1/*", new CheckParkLotController(request, response, boy));
        router.registerRoute("manage/2/*", new AddParkLotController(request, response, boy));
        return router;
    }

}

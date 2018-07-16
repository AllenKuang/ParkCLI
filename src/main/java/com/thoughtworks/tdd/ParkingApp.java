package com.thoughtworks.tdd;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParkingApp {
    static ParkingBoy parkingBoy=null;
    public static void main(String[] args) throws Exception {
         setParkingBoy();
         start();
    }

    public static void start() throws IOException{
        String input=tip();
        choose(input);
    }

    public static String tip() throws IOException {
        System.out.println("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
        Scanner in= new Scanner(System.in);
        String input=in.next();
        return input;
    }

    public static void choose(String input) throws IOException {
        if(input.equals("1")){
            int remainSpace =0;
            for(ParkingLot parkingLot:parkingBoy.getparkingLots()){
                remainSpace+=parkingLot.getSize();
            }
            if(remainSpace==0){
                System.out.println("车已停满，请晚点再来");
                start();
            }else {
                System.out.println("请输入车牌号:");
                Scanner in = new Scanner(System.in);
                Car car = new Car();
                car.setBrand(in.next());
                Receipt receipt = parkingBoy.park(car);
                System.out.println("停车成功，您的小票是：" + receipt.getId());
                start();
            }
        }else if(input.equals("2")){
            System.out.println("取车");
            System.out.println("请输入小票号:");
            Scanner in= new Scanner(System.in);
            ArrayList<Receipt> receipts=new ArrayList<>();

            for(ParkingLot parkingLot:parkingBoy.getparkingLots()){
                receipts.addAll(parkingLot.getparkcars().keySet());
                System.out.println(parkingLot.getparkcars().keySet());
            }
            Receipt whichReceipt=null;
            for(Receipt receipt:receipts){
                if(in.next().equals(receipt.getId())){
                    whichReceipt=receipt;
                    break;
                }
            }
            if(whichReceipt==null){
                System.out.println("非法小票，无法取出车，请查证后再输入");
                start();
            }else {
                Car car = parkingBoy.unPark(whichReceipt);
                System.out.println("车已取出，您的车牌号是: " + car.getBrand());
                //System.out.println(car.getBrand());
                start();
            }
        }else{
            System.out.println("非法指令，请查证后重新输入");
            start();
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

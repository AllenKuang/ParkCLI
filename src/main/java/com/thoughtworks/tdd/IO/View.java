package com.thoughtworks.tdd.IO;

public class View {
    public View(){
        showBeginView();
    }
    public void showBeginView(){
        System.out.println("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
    }
    public void showBeginViewParkManage(){
        System.out.println("1.停车服务\n" +
                "2.停车场管理\n" +
                "请输入您要进入的页面：");
    }
    public  void showIllegalInstructionView(){
        System.out.println("非法指令，请查证后重新输入");
    }
    public  void showisFullView(){
        System.out.println("车已停满，请晚点再来");
    }

    public  void showEnterBrandView(){
        System.out.println("请输入车牌号:");
    }

    public  void showReceiptIdView(String receiptId){
        System.out.println("停车成功，您的小票是：" + receiptId);
    }

    public static void showEnterReceiptIdView(){
        System.out.println("请输入小票号:");
    }

    public static void showBrandView(String brand){
        System.out.println("车已取出，您的车牌号是: " + brand);
    }

    public static void showIllegalReceiptIdView(){
        System.out.println("非法小票，无法取出车，请查证后再输入");
    }

}

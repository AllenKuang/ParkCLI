package com.thoughtworks.tdd.IO;

public class ManageView {
    public ManageView(){
        showBeginViewParkManage();
    }
    public void showBeginViewParkManage(){
        System.out.println("1.停车服务\n" +
                "2.停车场管理\n" +
                "请输入您要进入的页面：");
    }
    public  void showIllegalInstructionView(){
        System.out.println("非法指令，请查证后重新输入");
    }
}

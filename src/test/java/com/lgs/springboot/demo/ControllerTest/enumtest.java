package com.lgs.springboot.demo.ControllerTest;

public class enumtest {
    public static void main(String[] args) {
        DataEnum.FRIDAY.getData();
        System.out.println(DataEnum.MONDAY);
        String data = DataEnum.MONDAY.getData();
        System.out.println(data);
        DataEnum[] values = DataEnum.values();
        for (DataEnum value : values) {
            System.out.println(value.getData());
        }

      //  System.out.println(DataEnum.FRIDAY.compareTo(DataEnum.SUNDAY));


    }
}

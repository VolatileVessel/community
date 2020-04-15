package com.lgs.springboot.demo.ControllerTest;

import java.util.ArrayList;
import java.util.List;

public class demotest {
    public static void main(String[] args) {
        List<Student> arrayList = new ArrayList<Student>();
        Student student = new Student();
        student.setAge(1);
        student.setLove("毛豆");
        student.setName("1号");

        arrayList.add(0, student);

        for (Student array : arrayList) {
            System.out.println("name  " + array.getName() + "age  " + array.getAge() + "喜好  " + array.getLove());
        }
        System.out.println(arrayList.get(0));
        // System.out.println("arraylist的第一位是   "+  arrayList.get(0)+"   arrayList大小是"+arrayList.size());

    }
}

package com.lgs.springboot.demo.ControllerTest;

public class Student {
    private String name;
    private int age;
    private  String love;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int age, String love) {
        this.name = name;
        this.age = age;
        this.love = love;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", love='" + love + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }
}

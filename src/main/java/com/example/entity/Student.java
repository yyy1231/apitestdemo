package com.example.entity;

public class Student {
    String name = "洋洋";
    int age = 19;
    public void study(){
        System.out.println("hello yangyu");
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.study();
    }
}

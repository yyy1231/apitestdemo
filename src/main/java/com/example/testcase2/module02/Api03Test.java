package com.example.testcase2.module02;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Api03Test {

    @BeforeTest
    public void setUp(){
        System.out.println("com.example.testcase2.module02.Api03Test setUp()");
    }
    @Test
    public void api03_01Test(){
        System.out.println("api03_01Test()");
    }

    @Test
    public void api03_02Test(){
        System.out.println("api03_02Test()");
    }

    @Test
    public void api03_03Test(){
        System.out.println("api03_03Test()");
    }
}

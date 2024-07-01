package com.example.testcase2.module01;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Api01Test {

    @BeforeTest
    public void setUp(){
        System.out.println("com.example.testcase2.module01.Api01Test setUp()");
    }
    @Test(enabled = false)
    public void api01_01Test(){
        System.out.println("api01_01Test()");
    }

    @Test(dependsOnMethods = "com.example.testcase2.module01.Api02Test.api02_01Test")
    public void api01_02Test(){
        System.out.println("api01_02Test()");
    }

    @Test
    public void api01_03Test(){
        System.out.println("api01_03Test()");
    }
}

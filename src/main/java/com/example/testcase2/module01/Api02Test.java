package com.example.testcase2.module01;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Api02Test {

    @BeforeTest
    public void setUp(){
        System.out.println("com.example.testcase2.module01.Api02Test setUp()");
    }

    @Test
    public void api02_01Test(){
        System.out.println("api02_01Test()0728");
    }

    @Test
    public void api02_02Test(){
        System.out.println("api02_02Test()");
    }

    @Test
    public void api02_03Test(){
        System.out.println("api02_03Test()");
    }
}

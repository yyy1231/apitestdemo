package com.example.testcase2.module03;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Api05Test {
    @BeforeTest
    public void setUp(){
        System.out.println("api05Test setUp()");
    }
    @Test
    public void api05_01Test(){
        System.out.println("api05_01Test()");
    }

    @Test
    public void api05_02Test(){
        System.out.println("api05_02Test()");
    }

    @Test
    public void api05_03Test(){
        System.out.println("api05_03Test()");
    }
}

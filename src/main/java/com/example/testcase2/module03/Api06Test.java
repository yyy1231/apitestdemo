package com.example.testcase2.module03;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Api06Test {
    @BeforeTest
    public void setUp(){
        System.out.println("api06Test setUp()");
    }
    @Test
    public void api06_01Test(){
        System.out.println("api06_01Test()");
    }

    @Test
    public void api06_02Test(){
        System.out.println("api06_02Test()");
    }

    @Test
    public void api06_03Test(){
        System.out.println("api06_03Test()");
    }
}

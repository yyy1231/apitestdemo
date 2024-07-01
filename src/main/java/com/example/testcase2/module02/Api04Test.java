package com.example.testcase2.module02;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Api04Test {
    @BeforeTest
    public void setUp(){
        System.out.println("api04Test setUp()");
    }
    @Test
    public void api04_01Test(){
        System.out.println("api04_01Test()");
    }

    @Test
    public void api04_02Test(){
        System.out.println("api04_02Test()");
    }

    @Test
    public void api04_03Test(){
        System.out.println("api04_03Test()");
    }
    
    
    
}

package com.example;

import org.testng.TestNG;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        //加入一个一个的类
//        TestNG testNG = new TestNG();
//        testNG.setTestClasses(new Class[]{Api02Test.class, Api03Test.class});
//        testNG.run();

        // 测试套件XML
        XmlSuite suite = new XmlSuite();
        suite.setName("suite3");

        XmlTest test = new XmlTest(suite);
        test.setName("test3");
//        List<XmlClass> classes = new ArrayList<XmlClass>();
//        classes.add(new XmlClass("com.example.testcase2.module01.Api01Test"));
//        classes.add(new XmlClass("servers.testcase.LoginCase"));
//        test.setXmlClasses(classes) ;

        List<XmlPackage> packages = new ArrayList<>();
        packages.add(new XmlPackage("com.example.testcase2.module02"));
        test.setXmlPackages(packages);

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }

}
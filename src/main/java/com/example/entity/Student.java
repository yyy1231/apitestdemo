package com.example.entity;

import lombok.Data;
import java.util.Date;


@Data
public class Student {

    private int sno;


    private String sname;


    private String ssex;

    private Date sbirthday;
    private String clazz;
}

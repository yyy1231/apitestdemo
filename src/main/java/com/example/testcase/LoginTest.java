package com.example.testcase;

import com.alibaba.fastjson.JSONObject;
import com.example.util.HttpClientUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class LoginTest {
    HttpClientUtil httpClientUtil;
    String url = "https://reqres.in/api/login";

    @BeforeTest
    public void setUp(){
        httpClientUtil = new HttpClientUtil();
    }

    @Test
    public void loginSuccessTest() throws Exception {
        Map<String,String> formData = new HashMap<>();
        formData.put("email","eve.holt@reqres.in");
        formData.put("password","cityslicka");
        JSONObject jsonObject = httpClientUtil.sendPostByForm(url,formData);
        System.out.println("返回数据：" + jsonObject);
        String httpStatus = jsonObject.getString("HttpStatus");
        String token = jsonObject.getString("token");
        Assert.assertEquals(httpStatus,"200");
        Assert.assertNotNull(token,"成功返回token");
    }

    @Test
    public void loginUnsuccessTest() throws Exception {
        Map<String,String> formData = new HashMap<>();
        formData.put("email","peter@klaven");
        JSONObject jsonObject = httpClientUtil.sendPostByForm(url,formData);
        System.out.println("返回数据："  + jsonObject);
        String httpStatus = jsonObject.getString("HttpStatus");
        String error = jsonObject.getString("error");
        Assert.assertEquals(httpStatus,"400");
        Assert.assertEquals(error,"Missing password");
        boolean b = jsonObject.containsValue("Missing password");
        Assert.assertTrue(b);
        String str = jsonObject.toString();
        Boolean b1 = str.contains("Missing");
        Assert.assertTrue(b1);


    }

}

package com.example.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.util.ExcelUtil;
import com.example.util.HttpClientUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class RequestMethodTest2 {
    HttpClientUtil httpClientUtil;
    String url;
    String filePath = "src/main/resources/testdata1.xls";

    @BeforeTest
    public void setUp() {
        httpClientUtil = new HttpClientUtil();
    }

    /*
    注意：@Parameters，这种方法必须执行xml文件，而不是java文件，否则报错
    Parameter 'username' is required by @Test on method pamameterUse1 but has not been marked @Optional or defined
    */
    @Parameters("page")
    @Test
    public void getUserListTest(String page) {
        try {
            url = "https://reqres.in/api/users?" + "page=" + page;
            System.out.println("请求地址为：" + url);
            JSONObject jsonObject = httpClientUtil.sendGet(url);
            System.out.println("返回数据：" + jsonObject);
            //读取出参，第一层、第二层
            int httpStatus = jsonObject.getInteger("HttpStatus");
            String text = jsonObject.getJSONObject("support").getString("text");
            //获取"data":[{},{},{}]
            String lastName = jsonObject.getJSONArray("data")
                    .getJSONObject(0).getString("last_name");

            //断言
            Assert.assertEquals(httpStatus, 200);
//            Assert.assertEquals(lastName,"Bluth");
        } catch (Exception e) {
            System.out.println("异常了" + e);
        }
    }


    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][]{
                {new User("morpheus", "leader")},
                {new User("morpheus2", "leader2")}
        };
    }


    @DataProvider(name="userData2")
    public Object[][] userData2() throws IOException {
        String path = "src/main/resources/data.xls";
        return ExcelUtil.readObjDatas("src/main/resources/data.xls",0);
    }

    @Test(dataProvider = "userData2")
    public void insertUserTest(String para1,String para2) throws Exception {
        url = "https://reqres.in/api/users";
//        User user = new User("morpheus","leader");
        try {
            JSONObject req = JSON.parseObject(para1);
            JSONObject response = httpClientUtil.sendPostByJson(url, req);
            System.out.println("返回数据：" + response);
            Assert.assertEquals(response.getInteger("HttpStatus"), 201);
            Assert.assertEquals(response.getString("name"), "morpheus");
        } catch (Exception e) {
            System.out.println("异常了：" + e);
            ;
        }
    }

    @Test
    public void insertUserTest2() throws Exception {
        url = ExcelUtil.readStringDatas(filePath, 0, 1, 3);
        String reqData = ExcelUtil.readStringDatas(filePath, 0, 1, 4);
        JSONObject reqDataJson = JSON.parseObject(reqData);
        System.out.println("请求参数："+ reqDataJson.getClass().getName());
        JSONObject response = httpClientUtil.sendPostByJson(url, reqDataJson);
        System.out.println("返回数据：" + response);
        Assert.assertEquals(response.getInteger("HttpStatus"), 201);
        Assert.assertEquals(response.getString("name"), "morpheus");

    }

    @Test
    public void postByFormTest() throws Exception {
        url = "https://reqres.in/api/login";
        Map<String, String> formData = new HashMap<>();
        formData.put("email", "eve.holt@reqres.in");
        formData.put("password", "cityslicka");
        JSONObject response = httpClientUtil.sendPostByForm(url, formData);
        System.out.println("返回数据：" + response);
        Assert.assertEquals(response.getInteger("HttpStatus"), 200);
    }

    @Test
    public void putTest() throws Exception {
        url = "https://reqres.in/api/users/2";
        HashMap<String, String> requestHeader = new HashMap<>();
        requestHeader.put("Content-Type", "application/json");
        //对象转换为json字符串
        User user = new User("tester", "automation tester");
        String userJsonString = JSON.toJSONString(user);
        JSONObject response = httpClientUtil.sendPut(url, userJsonString, requestHeader);
        System.out.println("返回数据：" + response);
        int statusCode = response.getInteger("HttpStatus");
        String name = response.getString("name");
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(name, "tester");
    }

    @Test
    public void deleteTest() throws Exception {
        url = "https://reqres.in/api/users/2";
        int statusCode = httpClientUtil.sendDelete(url);
        Assert.assertEquals(statusCode, 204);
    }

}

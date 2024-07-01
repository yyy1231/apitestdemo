package com.example.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.util.ExcelUtil;
import com.example.util.HttpClientUtil;
import com.example.util.JsonParseUtil;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class RequestMethodTest {
    HttpClientUtil httpClientUtil;
    String url;
    String filePath = "src/main/resources/testdata1.xls";

    @BeforeTest
    public void setUp(){
        httpClientUtil = new HttpClientUtil();
    }

    @Test
    public void getUserListTest(){
        try{
            //从文件中读取请求地址、入参
            url = ExcelUtil.readStringDatas(filePath,0,1,8);
            String key = ExcelUtil.readStringDatas(filePath,0,1,9);
            Double value = ExcelUtil.readNumDatas(filePath,0,1,10);
            HashMap<String, String> params = new HashMap<>();
            //封装入参，String.valueOf(value)数值型转字符串
            params.put(key,String.valueOf(value));
            //发送请求，返回响应数据
            JSONObject jsonObject = httpClientUtil.sendGet(url,params);
            System.out.println("返回数据：" + jsonObject);
//            System.out.println("测试:"+ jsonObject.get("data"));
            //读取出参，第一层、第二层
            int httpStatus = jsonObject.getInteger("HttpStatus");
            String text = jsonObject.getJSONObject("support").getString("text");
            //获取"data":[{},{},{}]
//            JSONArray jsonArray  = jsonObject.getJSONArray("data");
//            String lastName = jsonArray.getJSONObject(0).getString("last_name");
            String lastName = jsonObject.getJSONArray("data")
                    .getJSONObject(0).getString("last_name");
//            System.out.println(lastName);

            //使用封装的工具类JsonParseUtil读取响应数据
//            String httpStatus = JsonParseUtil.getValueByJPath(jsonObject,"HttpStatus");
//            String text = JsonParseUtil.getValueByJPath(jsonObject,"support/text");
//            String last_name1 = JsonParseUtil.getValueByJPath(jsonObject,"data[0]/last_name");
//            System.out.println(last_name1);

            //断言
            Assert.assertEquals(httpStatus,200);
            Assert.assertEquals(lastName,"Bluth");
        }catch (Exception e){
            System.out.println("异常了"+e);
        }
    }


    @DataProvider(name="UserData")
    public Object[][] UserData(){
        return new Object[][]{
                {new User("morpheus", "leader")}
                };
    }

    @Test(dataProvider = "UserData")
    public void insertUserTest(Object user) throws Exception {
        url ="https://reqres.in/api/users";
//        User user = new User("morpheus","leader");
        try {
            JSONObject response = httpClientUtil.sendPostByJson(url,user);
            System.out.println("返回数据："+ response);
            Assert.assertEquals(response.getInteger("HttpStatus"),201);
            Assert.assertEquals(response.getString("name"),"morpheus");
        } catch (Exception e) {
            System.out.println("异常了：" + e);;
        }
    }

    @Test
    public void postByFormTest() throws Exception {
        url = "https://reqres.in/api/login";
        Map<String,String> formData = new HashMap<>();
        formData.put("email","eve.holt@reqres.in");
        formData.put("password","cityslicka");
        JSONObject response = httpClientUtil.sendPostByForm(url,formData);
        System.out.println("返回数据："+ response);
        Assert.assertEquals(response.getInteger("HttpStatus"),200);
    }

    @Test
    public void putTest() throws Exception {
        url = "https://reqres.in/api/users/2";
        HashMap<String,String> requestHeader = new HashMap<>();
        requestHeader.put("Content-Type","application/json");
        //对象转换为json字符串
        User user = new User("tester", "automation tester");
        String userJsonString = JSON.toJSONString(user);
        JSONObject response = httpClientUtil.sendPut(url,userJsonString,requestHeader);
        System.out.println("返回数据："+ response);
        int statusCode = response.getInteger("HttpStatus");
        String name = response.getString("name");
        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(name,"tester");
    }

    @Test
    public void deleteTest() throws Exception {
        url = "https://reqres.in/api/users/2";
        int statusCode = httpClientUtil.sendDelete(url);
        Assert.assertEquals(statusCode,204);
    }

}

package com.example.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.util.ExcelUtil;
import com.example.util.HttpClientUtil;
import com.example.util.JsonParseUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InsertUserTest {
    HttpClientUtil httpClientUtil;
    String url;
    String filePath = "src/main/resources/testdata1.xls";

    @BeforeTest
    public void setUp(){
        httpClientUtil = new HttpClientUtil();
    }

    @Test
    public void insertUserSuccessTest() throws Exception {
        try {
            url = "https://reqres.in/api/users";
            String excelparams = ExcelUtil.readStringDatas(filePath, 0, 1, 4);
            JSONObject jsonObj = JSON.parseObject(excelparams);
            System.out.println(jsonObj);
//            User user = new User("jack","actor");
//            JSONObject jsonObject = httpClientUtil.sendPostByJson(url,user);
            JSONObject jsonObject = httpClientUtil.sendPostByJson(url,jsonObj);
            System.out.println("返回数据："+ jsonObject);
            String httpStatus = JsonParseUtil.getValueByJPath(jsonObject,"HttpStatus");
            String name = JsonParseUtil.getValueByJPath(jsonObject,"name");
            String job = JsonParseUtil.getValueByJPath(jsonObject,"job");

            Assert.assertEquals(httpStatus,"201");
            Assert.assertEquals(name,"jack");
            Assert.assertEquals(job,"actor");
        } catch (Exception e) {
            System.out.println("异常了：" + e);;
        }

    }

}

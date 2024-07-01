package com.example.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.util.ExcelUtil;
import com.example.util.HttpClientUtil;
import org.testng.Assert;
import org.testng.annotations.Test;



public class InsertStudentTest {
    HttpClientUtil httpClientUtil;
    String url;
    String filePath = "src/main/resources/testdata1.xls";

    @Test
    public void insertStudentSuccessTest() throws Exception {
        httpClientUtil=new HttpClientUtil();
        url = "http://localhost:8081/test/student";
        String str =ExcelUtil.readStringDatas(filePath,0,4,4);
        JSONObject jsonObject =JSON.parseObject(str);
        System.out.println("从excel中读取入参：" + jsonObject);
        JSONObject resp = httpClientUtil.sendPostByJson(url,jsonObject);
        Assert.assertEquals(resp.getString("msg"),"操作成功");
        Assert.assertEquals(resp.getString("data"),"新增成功1");
        Assert.assertEquals(resp.getInteger("code"),1000);
    }
}

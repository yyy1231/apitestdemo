package com.example.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.util.ExcelUtil;
import com.example.util.HttpClientUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectStudentTest {
    HttpClientUtil httpClientUtil;
    String url;
    String filePath = "src/main/resources/testdata.xls";

    @Test
    public void selectStudentSuccessTest() throws Exception {
        httpClientUtil=new HttpClientUtil();
        url = "http://localhost:8081/test/student";
        JSONObject resp = httpClientUtil.sendGet(url);
        System.out.println(resp);
        Assert.assertEquals(resp.getString("msg"),"操作成功");
        Assert.assertEquals(resp.getInteger("HttpStatus"),200);
        Assert.assertNotNull(resp.getJSONArray("data").getJSONObject(0));
    }


}

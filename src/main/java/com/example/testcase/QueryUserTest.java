package com.example.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.util.ExcelUtil;
import com.example.util.HttpClientUtil;
import com.example.util.JsonStringToMap;
import com.example.util.StringToMap;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QueryUserTest {
    HttpClientUtil httpClientUtil;
    String url;
    String filePath = "src/main/resources/testdata1.xls";

    @BeforeTest
    public void setUp(){
        httpClientUtil = new HttpClientUtil();
    }

    public Object[] readCases(){
    return null;
    }

    @Test
    public void queryUserByPageSuccessTest(){
        try{
            url = ExcelUtil.readStringDatas(filePath,0,1,8);
            //方式一
//            String key = ExcelUtil.readStringDatas(filePath,0,1,9);
//            Double value = ExcelUtil.readNumDatas(filePath,0,1,10);
//            HashMap<String, String> params = new HashMap<>();
//            params.put(key,String.valueOf(value));
            //方式二
//            String params = ExcelUtil.readStringDatas(filePath,0,2,4);
//            HashMap<String,String> map = StringToMap.stringToMap(params);

            //方式三
            String params = ExcelUtil.readStringDatas(filePath,0,3,4);
            HashMap<String,String> map = JsonStringToMap.jsonStringToMap(params);

            System.out.println(params);
            JSONObject jsonObject = httpClientUtil.sendGet(url,map);
            System.out.println("返回数据：" + jsonObject);

            int httpStatus = jsonObject.getInteger("HttpStatus");
            JSONObject data = jsonObject.getJSONArray("data").getJSONObject(0);
            String lastName = jsonObject.getJSONArray("data")
                    .getJSONObject(0).getString("last_name");
            Assert.assertEquals(httpStatus,200);
            Assert.assertNotNull(data);
            Assert.assertNotNull(lastName);
        }catch (Exception e){
            System.out.println("异常了："+e);
        }


    }
}

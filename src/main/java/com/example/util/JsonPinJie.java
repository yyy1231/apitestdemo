package com.example.util;

import com.alibaba.fastjson.JSONObject;

public class JsonPinJie {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("showName","Template_14");
        jsonObject.put("name","Instance_17");
        jsonObject.put("namespace","system");
        jsonObject.put("description",new Integer(13219));
        jsonObject.put("templateId","12990");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("selectedInstance",jsonObject);
        System.out.println(jsonObject1);
    }
}

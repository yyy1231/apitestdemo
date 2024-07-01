package com.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonStringToMap {
    public static HashMap<String, String> jsonStringToMap(String param) {
        //param:ex，{"page":"2"}
        HashMap<String, String> map = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(param);
        for (Map.Entry<String,Object> entry:jsonObject.entrySet()) {
            map.put(entry.getKey(), (String) entry.getValue());
        }
//        for (Map.Entry<String,String> entry:map.entrySet()
//             ) {
//            System.out.println(entry.getKey()+ "="+entry.getValue());
//
//        }
        return map;
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "    \"name\": \"jack\",\n" +
                "    \"job\": \"actor\"\n" +
                "}";
        JsonStringToMap.jsonStringToMap(str);

    }
}

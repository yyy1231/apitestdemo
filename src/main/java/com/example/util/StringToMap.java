package com.example.util;

import java.util.HashMap;
import java.util.Map;

public class StringToMap {

    public static HashMap<String,String> stringToMap(String param){
        //param：ex,{page=2}
        HashMap<String,String> map = new HashMap<>();
        //去除{}
        String str1 = param.replace("{","");
        String str2 = str1.replace("}", "");
        String str3 = str2.trim();

        System.out.println("str3:" + str3);

        //根据逗号分隔
        String[] arr = str3.split(",");
        for (String str:arr) {
            System.out.println(str);

        }

        //put到map
        for(int i=0;i<arr.length;i++){
            String ele1 = arr[i].trim();
            System.out.println("ele1:"+ ele1);
            String[] arr2 = ele1.split("=");
            System.out.println(arr2);
            map.put(arr2[0],arr2[1]);
        }

        return map;
    }

    public static void main(String[] args) {
        String str = "{page=2,name=lucy}";
        HashMap<String,String> map = StringToMap.stringToMap(str);
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String key =entry.getKey();
            Object value =entry.getValue();
            System.out.println(key + "=" +value);
        }

    }
}

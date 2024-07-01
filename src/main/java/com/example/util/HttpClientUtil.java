package com.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

public class HttpClientUtil {
    private CloseableHttpClient httpClient;
    private CloseableHttpResponse response;
    private RequestConfig requestConfig;
    public String HTTPSTATUS = "HttpStatus";

    public HttpClientUtil() {
        requestConfig = RequestConfig.custom().setConnectTimeout(5000).
                setConnectionRequestTimeout(1000).setSocketTimeout(10000).build();
    }

    /**
     * @param connectTimeout           设置连接超时时间，单位毫秒。
     * @param connectionRequestTimeout 设置从connect Manager(连接池)获取 Connection
     *                                 超时时间，单位毫秒。这个属性是新加的属性，
     * @param socketTimeout            请求获取数据的超时时间(即响应时间)，单位 毫秒。
     *                                 如果访问一个接口，多少时间内无法返回数据， 就直接放弃此次调用。
     */
    public HttpClientUtil(int connectTimeout, int connectionRequestTimeout, int socketTimeout) {
        requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout).
                setConnectionRequestTimeout(connectionRequestTimeout).
                setSocketTimeout(socketTimeout).build();
    }

    public JSONObject sendGet(String url, HashMap<String, String> params, HashMap<String, String> headers)
            throws Exception {
        //创建HttpClient客户端，用于发送请求
        httpClient = HttpClients.createDefault();

        //拼接url?key=value
        if (params != null) {
            List<NameValuePair> pairs = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                if (!value.isEmpty()) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
            url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs), "UTF-8");
        }

        //创建httpGet请求，传入请求url
        HttpGet httpGet = new HttpGet(url);
        try {
            //设置请求配置
            httpGet.setConfig(requestConfig);
            //HttpGet添加请求头信息
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            //发送请求，获取响应数据
            response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            String responseEntityString = null;
            if (responseEntity != null) {
                responseEntityString = EntityUtils.toString(responseEntity, "UTF-8");
            }
            //释放请求，关闭连接
            EntityUtils.consume(responseEntity);
            JSONObject responseJson = JSON.parseObject(responseEntityString);
            responseJson.put(HTTPSTATUS, response.getStatusLine().getStatusCode());
            return responseJson;
        } finally {
            httpClient.close();
            response.close();
        }
    }

    public JSONObject sendGet(String url, HashMap<String, String> params) throws Exception {
        return this.sendGet(url, params, null);
    }

    public JSONObject sendGet(String url) throws Exception {
        return this.sendGet(url, null, null);
    }

    public JSONObject sendPostByJson(String url, Object object, HashMap<String, String> headers) throws Exception {
        httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setConfig(requestConfig);
            //设置请求主体
            String requestBody = JSON.toJSONString(object);
            StringEntity requestEntity = new StringEntity(requestBody, "utf-8");
            requestEntity.setContentType("application/json");
            httpPost.setEntity(requestEntity);

            //HttpPost添加请求头信息
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            //发送请求，获取返回数据
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseEntityString = null;
            if (responseEntity != null) {
                responseEntityString = EntityUtils.toString(responseEntity, "utf-8");
            }
            EntityUtils.consume(responseEntity);
            JSONObject responseJson = JSON.parseObject(responseEntityString);
            responseJson.put(HTTPSTATUS, response.getStatusLine().getStatusCode());
            return responseJson;
        } finally {
            httpClient.close();
            response.close();
        }
    }

    public JSONObject sendPostByJson(String url, Object object) throws Exception {
        return sendPostByJson(url, object, null);
    }

    public JSONObject sendPostByForm(String url, Map<String, String> formData, HashMap<String, String> headers) throws Exception {
        httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setConfig(requestConfig);
            //设置请求主体
            UrlEncodedFormEntity entity = null;
            if (formData.size() > 0) {
                ArrayList<BasicNameValuePair> list = new ArrayList<>();
                formData.forEach((key, value) -> list.add(new BasicNameValuePair(key, value)));
                entity = new UrlEncodedFormEntity(list, "utf-8");
            }
            entity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(entity);
            if (headers != null) {
                //设置头部信息
                Set<String> set = headers.keySet();
                for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
                    String key = iterator.next();
                    String value = headers.get(key);
                    httpPost.setHeader(key, value);
                }
            }
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseEntityString = null;
            if (responseEntity != null) {
                responseEntityString = EntityUtils.toString(responseEntity, "utf-8");
            }
            EntityUtils.consume(responseEntity);
            JSONObject jsonObject = JSON.parseObject(responseEntityString);
            jsonObject.put(HTTPSTATUS, response.getStatusLine().getStatusCode());
            return jsonObject;
        } finally {
            httpClient.close();
            response.close();
        }

    }

    public JSONObject sendPostByForm(String url, Map<String, String> formData) throws Exception {
        return sendPostByForm(url, formData, null);
    }


    public JSONObject sendPut(String url,String entityString,HashMap<String,String> headers) throws Exception{
        httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        try{
            httpPut.setConfig(requestConfig);
            httpPut.setEntity(new StringEntity(entityString,"utf-8"));
            if(headers != null){
                for (Map.Entry<String,String> entry:headers.entrySet()){
                    httpPut.setHeader(entry.getKey(),entry.getValue());
                }
            }
            //发送put请求
            response = httpClient.execute(httpPut);
            HttpEntity responseEntity = response.getEntity();
            String responseEntityString = null;
            if(responseEntity != null){
                responseEntityString = EntityUtils.toString(responseEntity,"utf-8");
            }
            EntityUtils.consume(responseEntity);
            JSONObject jsonObject = JSON.parseObject(responseEntityString);
            jsonObject.put(HTTPSTATUS,response.getStatusLine().getStatusCode());
            return jsonObject;
        }finally {
            httpClient.close();
            response.close();
        }
    }

    public int sendDelete(String url) throws Exception {
        httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        try{
            httpDelete.setConfig(requestConfig);
            response = httpClient.execute(httpDelete);
            int statusCode = response.getStatusLine().getStatusCode();
            return statusCode;
        }finally {
            httpClient.close();
            response.close();
        }
    }

}

package cn.edu.yzx.order.utils;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@UtilityClass
@SuppressWarnings("all")
public class AskGptUtil {

    /**
     * 聊天端点
     */
    String chatEndpoint = "https://api.openai.com/v1/chat/completions";
    /**
     * api密匙
     */
    String apiKey = "Bearer " + "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";

    public void sendGpt(String txt) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(chatEndpoint);
        RequestConfig.Builder customReqConf = RequestConfig.custom();
        customReqConf.setConnectTimeout(5000);
        customReqConf.setSocketTimeout(10000);
        customReqConf.setConnectionRequestTimeout(5000);
        request.setConfig(customReqConf.build());
        CloseableHttpResponse response = null;
        HttpEntity httpEntity = null;
        try{
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Authorization", apiKey);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("model", "gpt-3.5-turbo");
            List<Map<String, String>> dataList = new ArrayList<>();
            dataList.add(new HashMap<String, String>(){{
                put("role", "user");
                put("content", txt);
            }});
            paramMap.put("messages", dataList);
            paramMap.put("temperature", 0.7);
            StringEntity entity = new StringEntity("", "GBK");
            request.setEntity(entity);
            response = httpClient.execute(request);
            System.out.println("response = " + response);
        }catch (Exception e){
            throw e;
        }finally {
            if(httpEntity != null){
                EntityUtils.consume(httpEntity);
            }
            if (response != null){
                response.close();
            }
            httpClient.close();
        }
    }

    /**
     * 发送消息
     *
     * @param txt 内容
     * @return {@link String}
     */
    public static String chat(String txt) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>(){{
            put("role", "user");
            put("content", txt);
        }});
        paramMap.put("messages", dataList);
        paramMap.put("temperature", 0.7);
//        JSONObject json = new JSONObject(paramMap);
        JSONObject message = null;
        try {
            String body = HttpRequest.post(chatEndpoint)
                    .header("Authorization", apiKey)
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap))
                    .setConnectionTimeout(1000000)
                    .setReadTimeout(1000000)
                    .setHttpProxy("127.0.0.1", 7890)
                    .execute()
                    .body();
//            System.out.println("body = " + body);
            JSONObject jsonObject = JSONUtil.parseObj(body);
            JSONArray choices = jsonObject.getJSONArray("choices");
            JSONObject result = choices.get(0, JSONObject.class, Boolean.TRUE);
            message = result.getJSONObject("message");
//            return message.toString();
        } catch (HttpException e) {
            return "出现了http异常";
        } catch (ConvertException e) {
            return "出现了异常";
        }
        return message.getStr("content");
    }

    public static void main(String[] args) {
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("model", "gpt-3.5-turbo");
//        List<Map<String, String>> dataList = new ArrayList<>();
//        dataList.add(new HashMap<String, String>(){{
//            put("role", "user");
//            put("content", "计算机");
//        }});
//        paramMap.put("messages", dataList);
//        String s = JSONUtil.toJsonStr(paramMap);
//        System.out.println("s = " + s);
//        System.out.println(chat("你是谁"));

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入问题（退出exit）：");
            String next = scanner.next();
            if ("exit".equals(next)) break;
            String chat = chat(next);
            System.out.println("chat = " + chat);
        }
//        try {
//            sendGpt("计算机");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

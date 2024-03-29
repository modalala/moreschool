package com.fosu.study.business;

import com.fosu.study.commons.utils.MapperUtils;
import com.fosu.study.commons.utils.OkHttpClientUtil;
import com.google.common.collect.Maps;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OKHttp3Tests {


    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testGet() {
        String url = "https://www.baidu.com";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testPost() {
        String url = "http://localhost:9001/oauth/token";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456")
                .add("grant_type", "password")
                .add("client_id", "client")
                .add("client_secret", "secret")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUtilsPost(){
        String url = "http://localhost:9001/oauth/token";
        Map<String,String> params = Maps.newHashMap();
        params.put("username", "admin");
        params.put("password", "123456");
        params.put("grant_type", "password");
        params.put("client_id", "client");
        params.put("client_secret", "secret");


        try {
            Response response = OkHttpClientUtil.getInstance().postData(url, params);
            String jsonString = response.body().string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));

            //System.out.println(response.body().string());
            System.out.println(token);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void passwordEncode(){

        String pass = "admin";

        String hashPass = bCryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bCryptPasswordEncoder.matches("admin",hashPass);
        System.out.println(f);
    }
}

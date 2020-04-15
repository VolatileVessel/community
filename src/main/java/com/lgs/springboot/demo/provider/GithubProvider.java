package com.lgs.springboot.demo.provider;

import com.alibaba.fastjson.JSON;
import com.lgs.springboot.demo.DTO.AccessTokenDTO;
import com.lgs.springboot.demo.DTO.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * OKHttp
 * @Componet 实例化   一个类，初始化
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO),mediaType  );
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();

                String token = string.split("&")[0].split("=")[1];
                return token;
                
        } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }
    public GithubUser getUser(String accesstoken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accesstoken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return  githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

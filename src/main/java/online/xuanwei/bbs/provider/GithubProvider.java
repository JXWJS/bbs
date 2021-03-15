package online.xuanwei.bbs.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import online.xuanwei.bbs.dto.AccessTokenDTO;
import online.xuanwei.bbs.dto.GithubUser;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GithubProvider {


    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        final MediaType mediaType = MediaType.get("application/json; charset=utf-8");
         final String  url = "https://github.com/login/oauth/access_token";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            String token = string.split("&")[0].split("=")[1];

            System.out.println("token:" + token);
            return token;
        } catch (Exception e) {
        }
    return  null;
    }


    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string =  response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }

        return null;
    }
}

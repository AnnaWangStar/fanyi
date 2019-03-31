package com.kai.dai.fanyi.ability;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kai.dai.fanyi.interfaces.TranslateEN2CN;
import com.kai.dai.fanyi.model.response.BaiduResponse;
import com.kai.dai.fanyi.utils.HttpUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
public class BaiduTranslateEN2CNImpl implements TranslateEN2CN {
    Gson gson = new Gson();

    @Override
    public String translate(String context) {
        try {
            String resultStr = HttpUtils.sendGet("http://fanyi.baidu.com/transapi?from=auto&to=zh&query=" + context);
//            HttpResponse<String> response = Unirest.get("http://fanyi.baidu.com/transapi?from=auto&to=zh&query=" + context)
//                    .header("Cache-Control", "no-cache")
//                    .header("Postman-Token", "90c26ed1-d6d8-fc01-0870-d208496fa607")
//                    .asString();
//            resultStr = response.getBody();
            JsonObject jsonObject = gson.fromJson(resultStr, JsonObject.class);
//            jsonObject.getAsJsonArray("data").get(0).getAsJsonObject().get("dst");
            BaiduResponse.DataResponse temp = gson.fromJson(resultStr, BaiduResponse.class).getData().get(0);
            if (temp.getSrc().equals(context)) {
                return temp.getDst();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

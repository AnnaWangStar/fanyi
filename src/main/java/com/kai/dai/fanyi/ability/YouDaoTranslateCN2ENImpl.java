package com.kai.dai.fanyi.ability;

import com.google.gson.Gson;
import com.kai.dai.fanyi.interfaces.TranslateCN2EN;
import com.kai.dai.fanyi.model.response.YoudaoResponse;
import com.kai.dai.fanyi.utils.HttpUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
public class YouDaoTranslateCN2ENImpl implements TranslateCN2EN {
    Gson gson = new Gson();

    @Override
    public String translate(String context) {
        try {
            String urlEncodedStr = URLEncoder.encode(context, "utf-8");
            String resultStr = HttpUtils.sendGet("http://fanyi.youdao.com/translate?doctype=json&type=AUTO&i=" + urlEncodedStr);
//            HttpResponse<String> response = Unirest.get("http://fanyi.youdao.com/translate?doctype=json&type=AUTO&i=" + urlEncodedStr)
//                    .header("Cache-Control", "no-cache")
//                    .header("Postman-Token", "4d46ca54-7aae-3935-5acc-1d8efcf4648b")
//                    .asString();
//
//            resultStr = response.getBody();
            YoudaoResponse youdaoResponse = gson.fromJson(resultStr, YoudaoResponse.class);
//            如果状态码为0
            if (youdaoResponse.getErrorCode() == 0) {
                YoudaoResponse.TranslateResult translateResult = youdaoResponse.getTranslateResult().get(0).get(0);
//                如果和context匹配
                if (context.equals(translateResult.getSrc())) {
                    return translateResult.getTgt();
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}

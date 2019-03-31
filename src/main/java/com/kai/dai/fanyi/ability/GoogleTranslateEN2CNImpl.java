package com.kai.dai.fanyi.ability;

import com.google.gson.Gson;
import com.kai.dai.fanyi.interfaces.TranslateEN2CN;
import com.kai.dai.fanyi.model.response.GoogleResponse;
import com.kai.dai.fanyi.utils.HttpUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class GoogleTranslateEN2CNImpl implements TranslateEN2CN {
    Gson gson = new Gson();

    @Override
    public String translate(String context) {
        try {
            String resultStr = null;
            resultStr = HttpUtils.sendGet("http://translate.google.cn/translate_a/single?client=gtx&dt=t&dj=1&ie=UTF-8&sl=auto&tl=zh-CN&q=" + context);
//            HttpResponse<String> response = Unirest.get("http://translate.google.cn/translate_a/single?client=gtx&dt=t&dj=1&ie=UTF-8&sl=auto&tl=zh-CN&q=" + context)
//                    .header("Cache-Control", "no-cache")
//                    .header("Postman-Token", "7f8b82d9-b4a2-4885-c573-82876be9db13")
//                    .asString();
//            resultStr = response.getBody();
            GoogleResponse.Sentence sentence = gson.fromJson(resultStr, GoogleResponse.class)
                    .getSentences()
                    .get(0);
            if (sentence.getOrig().equals(context)) {
                return sentence.getTrans();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}

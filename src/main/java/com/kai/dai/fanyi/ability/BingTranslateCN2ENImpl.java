package com.kai.dai.fanyi.ability;

import com.kai.dai.fanyi.interfaces.TranslateCN2EN;
import com.kai.dai.fanyi.utils.HttpUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BingTranslateCN2ENImpl implements TranslateCN2EN {
    Pattern pattern = Pattern.compile(">[\\w\\s]+");

    @Override
    public String translate(String context) {
        try {
            String urlEncodedStr = URLEncoder.encode(context, "utf-8");
            String resultStr = null;
            resultStr = HttpUtils.sendGet("http://api.microsofttranslator.com/v2/Http.svc/Translate?appId=AFC76A66CF4F434ED080D245C30CF1E71C22959C&from=&to=en&text=" + urlEncodedStr);
//            HttpResponse<String> response = Unirest.get("http://api.microsofttranslator.com/v2/Http.svc/Translate?appId=AFC76A66CF4F434ED080D245C30CF1E71C22959C&from=&to=en&text=" + urlEncodedStr)
//                    .header("Cache-Control", "no-cache")
//                    .header("Postman-Token", "934a298f-73a7-65aa-e048-f84c428e3183")
//                    .asString();
//            resultStr = response.getBody();
//            resultStr = strRegx(resultStr);
            resultStr = strSubMethod(resultStr);
//            resultStr = strXmlGet(resultStr);
            return resultStr;
        } catch (Exception e) {
            return null;
        }
    }

    private String strXmlGet(String resultStr) throws DocumentException {
        Document doc = null;

        // 读取并解析XML文档
        // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
        //
        // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
        // Document document = reader.read(new File("User.hbm.xml"));
        // 下面的是通过解析xml字符串的
        doc = DocumentHelper.parseText(resultStr); // 将字符串转为XML

        Element rootElt = doc.getRootElement(); // 获取根节点
        resultStr = rootElt.getText();
        return resultStr;
    }

    private String strRegx(String resultStr) {
        Matcher matcher = pattern.matcher(resultStr);
        if (matcher.find()) {
            resultStr = matcher.group();
            resultStr = resultStr.substring(1, resultStr.length());
        }
        return resultStr;
    }

    private String strSubMethod(String resultStr) {
        resultStr = resultStr.substring(68, resultStr.length() - 9);
        return resultStr;
    }
}

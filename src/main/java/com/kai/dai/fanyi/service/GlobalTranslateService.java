package com.kai.dai.fanyi.service;

import com.kai.dai.fanyi.interfaces.TranslateCN2EN;
import com.kai.dai.fanyi.interfaces.TranslateEN2CN;
import com.kai.dai.fanyi.model.bean.TranslateResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class GlobalTranslateService {
    @Resource(name = "googleTranslateEN2CNImpl")
    private TranslateEN2CN translateEN2CN;
    @Resource(name = "youDaoTranslateCN2ENImpl")
    TranslateCN2EN translateCN2EN;
    @Resource
    SpiltWordService spiltWordService;
    @Resource
    WriteFileService writeFileService;

    public void fanyi2Hanyu(String inputPath, String outPutPath) throws Exception {
        Map<String, Integer> map = spiltWordService.spilt(inputPath);
        Map<String, TranslateResult> resultMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            String result = translateEN2CN.translate(key);
            TranslateResult translateResult = new TranslateResult(key, result, entry.getValue());
            System.out.println("运行到" + key);
            resultMap.put(key, translateResult);
        }
        writeFileService.writeFile(outPutPath, resultMap);
    }

    public void fanyi2Yinyu(String inputPath, String outPutPath) throws Exception {
        Map<String, Integer> map = spiltWordService.spilt(inputPath);
        Map<String, TranslateResult> resultMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            String result = translateCN2EN.translate(key);
            TranslateResult translateResult = new TranslateResult(key, result, entry.getValue());
            System.out.println("运行到" + key);
            resultMap.put(key, translateResult);
        }
        writeFileService.writeFile(outPutPath, resultMap);
    }
}

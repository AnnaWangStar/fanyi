package com.kai.dai.fanyi.service;

import com.kai.dai.fanyi.dao.CnDao;
import com.kai.dai.fanyi.dao.EnDao;
import com.kai.dai.fanyi.interfaces.TranslateCN2EN;
import com.kai.dai.fanyi.interfaces.TranslateEN2CN;
import com.kai.dai.fanyi.model.bean.Cn;
import com.kai.dai.fanyi.model.bean.En;
import com.kai.dai.fanyi.model.bean.TranslateResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class GlobalTranslateService {
    @Resource(name = "baiduTranslateEN2CNImpl")
    private TranslateEN2CN translateEN2CN;
    @Resource(name = "youDaoTranslateCN2ENImpl")
    TranslateCN2EN translateCN2EN;
    @Resource
    SpiltWordService spiltWordService;
    @Resource
    WriteFileService writeFileService;
    @Resource
    EnDao enDao;
    @Resource
    CnDao cnDao;

    public void fanyi2Hanyu(String inputPath, String outPutPath) throws Exception {
        writeFileService.writeFile(outPutPath, fanyi2Hanyu(inputPath));
    }

    public Map<String, TranslateResult> fanyi2Hanyu(String inputPath) throws Exception {
        Map<String, Integer> map = spiltWordService.spilt(inputPath);
        Map<String, TranslateResult> resultMap = getStringTranslateResultMap(map);
        return resultMap;
    }

    public Map<String, TranslateResult> getStringTranslateResultMap(Map<String, Integer> map) {
        Map<String, TranslateResult> resultMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            String result;
//            如果数据库中有该字段，直接返回
            En temp = enDao.getBySrc(key);
            if (temp != null) {
                result = temp.getDest();
                System.out.println("从数据库查询的结果：" + result);
            } else {
                result = translateEN2CN.translate(key);
                En en = new En();
                en.setSrc(key);
                en.setDest(result);
                int temp1 = enDao.insertEn(en);
                if (temp1 == 1) {
                    System.out.println("从接口查询的结果：" + result);
                }
            }
            TranslateResult translateResult = new TranslateResult(key, result, entry.getValue());
            System.out.println("运行到" + key);
            resultMap.put(key, translateResult);
        }
        return resultMap;
    }

    public Map<String, TranslateResult> fanyi2HanyuWithStr(String ctx) throws Exception {
        Map<String, Integer> map = spiltWordService.spiltWithStr(ctx);
        Map<String, TranslateResult> resultMap = getStringTranslateResultMap(map);
        return resultMap;
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

    public Map<String, TranslateResult> fanyi2YinyuStr(String str) throws Exception {
        Map<String, Integer> map = spiltWordService.spiltWithCnStr(str);
        Map<String, TranslateResult> resultMap = new LinkedHashMap<>();
        String result;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
//            从DB中查询key，如果存在直接返回
            Cn temp = cnDao.getBySrc(key);
            if (temp != null) {
                result = temp.getDest();
                System.out.println("从DB查询：" + key);

            } else {
                result = translateCN2EN.translate(key);
                Cn cn = new Cn();
                cn.setDest(result);
                cn.setSrc(key);
                cnDao.insertCn(cn);
                System.out.println("从接口查询" + key);

            }
            TranslateResult translateResult = new TranslateResult(key, result, entry.getValue());
            resultMap.put(key, translateResult);
        }
        return resultMap;
    }
}

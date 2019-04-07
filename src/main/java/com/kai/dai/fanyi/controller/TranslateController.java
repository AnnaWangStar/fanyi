package com.kai.dai.fanyi.controller;

import com.google.gson.Gson;
import com.kai.dai.fanyi.service.GlobalTranslateService;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Date;

@RestController
public class TranslateController {

    Gson gson = new Gson();

    @Resource
    GlobalTranslateService service;

    @RequestMapping("/en")
    public String tanslateEn(String path) {
        try {
            Object result = service.fanyi2Hanyu("C:\\2018\\qunargit\\fanyi\\src\\main\\resources\\folder\\english.txt");
            return gson.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        while (bufferedReader.ready()) {
            sb.append(bufferedReader.readLine());
        }
        Object result;
        if (type.equals("en")) {
//          从英文翻译成中文
            result = service.fanyi2HanyuWithStr(sb.toString());
        } else if (type.equals("cn")) {
//            从中文翻译成英文
            result = service.fanyi2YinyuStr(sb.toString());
        } else {
            result = null;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("历时：" + (endTime - startTime));
        return gson.toJson(result);
    }
}

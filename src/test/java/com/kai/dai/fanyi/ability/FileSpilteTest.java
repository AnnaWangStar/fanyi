package com.kai.dai.fanyi.ability;

import com.kai.dai.fanyi.interfaces.Translate;
import com.kai.dai.fanyi.service.GlobalTranslateService;
import com.kai.dai.fanyi.service.SpiltWordService;
import com.kai.dai.fanyi.service.WriteFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FileSpilteTest {

    @Resource
    SpiltWordService spiltWordService;

    @Resource
    GlobalTranslateService globalTranslateService;

    @Resource
    WriteFileService writeFileService;


    @Test
    public void test1() {
        try {
            Map<String, Integer> resultMao = spiltWordService.spilt("C:\\2018\\qunargit\\fanyi\\src\\main\\resources\\folder\\test.txt");
            writeFileService.writeFile("C:\\2018\\qunargit\\fanyi\\src\\main\\resources\\folder\\result.txt", resultMao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEnCn() {
        try {
            DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
            String s = format.format(new Date());
            globalTranslateService.fanyi2Hanyu("C:\\2018\\qunargit\\fanyi\\src\\main\\resources\\folder\\english.txt",
                    "C:\\2018\\qunargit\\fanyi\\src\\main\\resources\\folder\\EnglishResult" + s + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCnEn() {
        try {
            DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
            String s = format.format(new Date());
            globalTranslateService.fanyi2Yinyu("C:\\2018\\qunargit\\fanyi\\src\\main\\resources\\folder\\chinese.txt",
                    "C:\\2018\\qunargit\\fanyi\\src\\main\\resources\\folder\\chineseResult" + s + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
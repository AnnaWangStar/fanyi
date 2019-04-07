package com.kai.dai.fanyi.dao;

import com.kai.dai.fanyi.model.bean.Cn;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CnDaoTest {
    @Resource
    CnDao cnDao;

    @Test
    public void test3() {
        String src = "猫咪";
        Cn temp = cnDao.getBySrc(src);
        System.out.println(temp);
    }

    @Test
    public void test4() {
        Cn cn = new Cn();
//        Cn.setId(2);
        cn.setSrc("狗");
        cn.setDest("dog");
        int temp = cnDao.insertCn(cn);
        System.out.println(temp);
    }

    @Test
    public void test5() {
        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
//        StopRecognition filter = new StopRecognition();
//        filter.insertStopWords("我"); //过滤单词
//        filter.insertStopWords("你");
//        filter.insertStopWords("的");
//        filter.insertStopWords(")");
//        filter.insertStopWords("(");
        long s1 =  System.currentTimeMillis();
        System.out.println(ToAnalysis.parse(str));
        long s2 =  System.currentTimeMillis();
        System.out.println(s2-s1);
        System.out.println(BaseAnalysis.parse(str));
        long s3 =  System.currentTimeMillis();
        System.out.println(s3-s2);
    }
}
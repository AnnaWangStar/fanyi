package com.kai.dai.fanyi.ability;

import com.kai.dai.fanyi.interfaces.Translate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class YouDaoTranslateImplTest {

    @Resource(name = "youDaoTranslateCN2ENImpl")
    Translate translate1;

    @Test
    public void testYoudaoTranslate() {
//        Translate translate = new YouDaoTranslateCN2ENImpl();
        String result = translate1.translate("计算");
        System.out.println(result);
    }

    @Test
    public void testBingTranslate() {
        Translate translate = new BingTranslateCN2ENImpl();
        realDoSth("beer", translate);
        realDoSth("计算", translate);
    }

    @Test
    public void testGoogleTranslate() {
        Translate translate = new GoogleTranslateEN2CNImpl();
        realDoSth("phone2", translate);
    }

    @Test
    public void testBaiduTranslate() {
        Translate translate = new BaiduTranslateEN2CNImpl();
        realDoSth("what's", translate);
    }

    private void realDoSth(String context, Translate translate) {
        String result = translate.translate(context);
//        <string xmlns="http://schemas.microsoft.com/2003/10/Serialization/">
        System.out.println("-------------分割线----------------");
        System.out.println(result);
    }

}
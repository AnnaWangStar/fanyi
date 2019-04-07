package com.kai.dai.fanyi.dao;

import com.kai.dai.fanyi.model.bean.En;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EnDaoTest {
    @Resource
    EnDao enDao;

    @Test
    public void test1() {
        String src="cat";
        En temp = enDao.getBySrc(src);
        System.out.println(temp);
    }

    @Test
    public void test2() {
        En en = new En();
//        en.setId(2);
        en.setSrc("dog");
        en.setDest("的");
        int temp = enDao.insertEn(en);
        System.out.println(temp);
    }
}
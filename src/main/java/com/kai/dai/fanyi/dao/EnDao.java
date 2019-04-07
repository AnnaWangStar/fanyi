package com.kai.dai.fanyi.dao;

import com.kai.dai.fanyi.model.bean.En;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnDao {
    public En getBySrc(String src);
    public int insertEn(En en);
}

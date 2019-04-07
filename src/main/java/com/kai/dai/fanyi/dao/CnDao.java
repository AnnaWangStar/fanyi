package com.kai.dai.fanyi.dao;

import com.kai.dai.fanyi.model.bean.Cn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CnDao {
    public Cn getBySrc(String src);
    public int insertCn(Cn cn);
}

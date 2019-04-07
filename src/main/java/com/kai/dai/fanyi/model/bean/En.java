package com.kai.dai.fanyi.model.bean;

import lombok.Data;

import java.util.Date;

@Data
public class En {
    private int id;
    private String src;
    private String dest;
    private Date createTs;
    private Date updateTs;
}

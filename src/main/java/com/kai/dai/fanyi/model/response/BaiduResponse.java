package com.kai.dai.fanyi.model.response;

import lombok.Data;

import java.util.List;
@Data
public class BaiduResponse {
    List<DataResponse> data;

    @Data
    public class DataResponse{
        String dst;
        String src;
    }
}

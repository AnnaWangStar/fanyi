package com.kai.dai.fanyi.model.response;

import lombok.Data;

import java.util.List;

@Data
public class GoogleResponse {
    List<Sentence> sentences;

    @Data
    public class Sentence{
        String trans;
        String orig;
    }

}

package com.kai.dai.fanyi.model.response;

import lombok.Data;

import java.util.List;

/**
 * {
 * "type": "ZH_CN2EN",
 * "errorCode": 0,
 * "elapsedTime": 1,
 * "translateResult": [
 * [
 * {
 * "src": "计算",
 * "tgt": "To calculate"
 * }
 * ]
 * ]
 * }
 */
@Data
public class YoudaoResponse {
    int errorCode;
    /**
     * 第一个数组代表每个要翻译的单词
     * 第二个数组中的TranslateResult 代表翻译的结果
     */
    List<List<TranslateResult>> translateResult;

    @Data
    public static class TranslateResult {
        String src;
        String tgt;
    }
}

package com.kai.dai.fanyi.model.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TranslateResult {
    String word;
    String result;
    int keyCount;

    public TranslateResult(String word, String result, int keyCount) {
        this.word = word;
        this.result = result;
        this.keyCount = keyCount;
    }

    @Override
    public String toString() {
        return "单词：" + word + "翻译结果：" + result + "  出现次数：" + keyCount;
    }
}

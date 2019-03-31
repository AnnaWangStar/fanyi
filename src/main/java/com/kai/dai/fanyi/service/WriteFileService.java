package com.kai.dai.fanyi.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Service
public class WriteFileService {

    public <K extends Object, V extends Object> void writeFile(String outPath, Map<K, V> map) throws IOException {
        File wordFile = new File(outPath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(wordFile));
        for (Map.Entry<K, V> entry : map.entrySet()) {
            bw.write(entry.getKey() + ":" + entry.getValue());
            bw.newLine();
        }
        bw.close();
    }
}

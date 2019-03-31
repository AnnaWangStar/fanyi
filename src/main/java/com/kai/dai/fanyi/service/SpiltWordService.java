package com.kai.dai.fanyi.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * 切分文件
 */
@Service
public class SpiltWordService {

    public Map<String, Integer> spilt(String filePath) throws Exception {
        File wordFile = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(wordFile));
        StringBuffer sb = new StringBuffer();
        while (br.ready()) {
            String line = br.readLine();
            sb.append(" ");
            sb.append(line);
        }
        StringTokenizer st = new StringTokenizer(sb.toString(), " ,?.!:\"\"''\n#");
        List<String> wordList = new ArrayList<>();
        while (st.hasMoreElements()) {
            wordList.add(st.nextToken().trim().toLowerCase());
        }
        System.out.println("---------------------------begin-----------------------------");
        System.out.println(wordList);
        HashMap<String, Integer> tempMap = new HashMap();
        String key;
        for (String word : wordList) {
            Integer value = tempMap.get(word);
            if (value != null) {
                tempMap.put(word, value + 1);
            } else {
                tempMap.put(word, 1);
            }
//            wxCode(wordList, tempMap, i);
        }

        Map<String, Integer> sortMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList(tempMap.entrySet());
        Collections.sort(list,
                (o1, o2) -> o1.getValue().compareTo(o2.getValue())
        );
        System.out.println(list);
        Map map = new LinkedHashMap();
        for (Map.Entry<String, Integer> entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }
        System.out.println(map);

        System.out.println("---------------------------end-----------------------------");
        br.close();
        return map;
    }

    private void wxCode(List<String> wordList, HashMap<String, Integer> tempMap, int i) {
        String key;
        Iterator keys = tempMap.keySet().iterator();
        boolean flag = false;
        while (keys.hasNext()) {
            key = (String) keys.next();
            if (wordList.get(i).equals(key)) {
                int temp = tempMap.get(key) + 1;
                tempMap.put(key, temp);
                flag = true;
                break;
            }
        }
        if (flag == false) {
            tempMap.put(wordList.get(i), 1);
        }
    }

}

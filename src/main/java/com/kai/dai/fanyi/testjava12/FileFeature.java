package com.kai.dai.fanyi.testjava12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FileFeature {
    public static Path path = Path.of("C:\\2018\\qunargit\\fanyi\\src\\main\\resources\\folder\\", "english.txt");

    public static void main(String[] args) throws IOException {
        readFile();
    }

    private static void readFile() throws IOException {
        String result = null;
        result = Files.readAllLines(path).stream().filter((str) -> str.contains("d")).collect(Collectors.toList()).toString();
        System.out.println("---------------------------------");
        System.out.println(result);
        System.out.println("---------------------------------");
        result = Files.readString(path);
        System.out.println(result);
    }
}

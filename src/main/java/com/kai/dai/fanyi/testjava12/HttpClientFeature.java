package com.kai.dai.fanyi.testjava12;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientFeature {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
//          .header("cache-control", "no-cache")
//                .header("Postman-Token", "d0d5024f-7174-4539-a50c-7dac500dc9fb")
        HttpResponse<String> response =
                httpClient.send(HttpRequest.newBuilder().GET()
                        .header("cache-control", "no-cache")
                        .header("Postman-Token", "d0d5024f-7174-4539-a50c-7dac500dc9fb")
                        .uri(URI.create("https://www.so.com"))
                        .uri(URI.create("http://www.baidu.com"))
                        .build(), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}

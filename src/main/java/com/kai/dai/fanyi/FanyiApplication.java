package com.kai.dai.fanyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kai.dai.fanyi"})
public class FanyiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanyiApplication.class, args);
    }

}

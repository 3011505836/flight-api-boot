package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.fh.mapper")
@SpringBootApplication
public class FlightApiBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightApiBootApplication.class, args);
    }

}

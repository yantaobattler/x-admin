package com.tky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tky.*.mapper")
public class XAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(XAdminApplication.class);
    }
}

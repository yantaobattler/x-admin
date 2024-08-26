package com.tky.user.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class User {
    private Integer user_id;
    private String username;
    private String password;
    private String chinesename;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date create_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date last_time;
    private String disabled;
}

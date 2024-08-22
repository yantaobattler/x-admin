package com.tky.user.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Menu {
    private Integer sys_menu_id;
    private String title;
    private String href;
    private String icon;
    private Integer pid;
    private String disabled;
    private String target;
    private Integer sort;
    private String remark;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_at;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date update_at;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date delete_at;
    
}

package com.tky.zhanpin.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Zhanpin implements Serializable {
    private Integer zhanpinId;
    
    private String name;
    
    private String address;
    
    private Integer deptId;
    
    
//  private Double sal;
    
    
//    private String sex;
    private String qixian;
        
//    private Integer age;
    private Integer num;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date birthday;
    private Date yijiao;
    
    
    
    private String company;//+
    
    private String pname;//+
    
    private String ptel;//+
    
    private String beizhu;//+
    
    private String qyname;//+
    
    private String qytel;//+
    
    private String address1;//+

    private ZhanpinDept dept;
}

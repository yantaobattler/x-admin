package com.tky.zhanpin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ZhanpinDept implements Serializable {
    private Integer deptId;
    private String deptName;
}

package com.tky.user.entity;

import lombok.Data;

@Data
public class Role {
    private Integer role_id;
    private String role_name;
    private String description;
    private String disabled;
    private Integer sort;
}

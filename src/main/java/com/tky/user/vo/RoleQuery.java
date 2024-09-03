package com.tky.user.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.tky.common.vo.Page;

import java.util.Date;

@Data
public class RoleQuery extends Page {
    private String role_name;
    private String description;
    private String disabled;
}

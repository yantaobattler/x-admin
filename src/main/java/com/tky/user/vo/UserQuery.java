package com.tky.user.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.tky.common.vo.Page;

import java.util.Date;

@Data
public class UserQuery extends Page {
    private String username;
    private String chinesename;
    private String disabled;
}

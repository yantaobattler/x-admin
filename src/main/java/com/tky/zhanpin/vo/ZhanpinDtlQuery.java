package com.tky.zhanpin.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.tky.common.vo.Page;

import java.util.Date;

@Data
public class ZhanpinDtlQuery extends Page {

    private Integer zhanpinId;
}

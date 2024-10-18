package com.tky.meeting.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.tky.common.vo.Page;

import java.util.Date;

@Data
public class MeetingRoomQuery extends Page {
    private String roomname;
    private String building;
    private String disabled;

}

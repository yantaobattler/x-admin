package com.tky.meeting.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.tky.common.vo.Page;

import java.util.Date;

@Data
public class RoomStatusQuery extends Page {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buliding;
	private String roomid;
    private String startdate;
    private String people;

}

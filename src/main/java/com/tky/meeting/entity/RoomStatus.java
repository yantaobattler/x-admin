package com.tky.meeting.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class RoomStatus implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roomid;
    private String roomname;//会议室名称
    
    private Date startdate; // 会议室类型
    private String status;// 预约时间状态位图30


}

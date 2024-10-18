package com.tky.meeting.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class MeetingRoom implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roomid;
	private String park;//园区
	private String building;//楼宇
    private String roomname;//会议室名称
    
    private Integer num;//建议人数
    private Integer maxnum;//最大人数
    private String roomtype; // 会议室类型
    private String device;//设备设施
    private String disabled;//是否可预订
   
    private String address1;//图片1
    private String address2;//图片2
    private String address3;//图片3


}

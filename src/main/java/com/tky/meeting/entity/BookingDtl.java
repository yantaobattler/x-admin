package com.tky.meeting.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class BookingDtl implements Serializable {
    
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private Integer booking_id;
	private Integer user_id;
	private Integer roomid;
	private String username;//
    
    private Date bookingdate;//提交预定日期
    private Date startdate;//开会日期
    private Date starttime; // 开会时间
    private Date endtime;//结束时间
    private String pay_status;//缴费状态 0-未缴费，1-已缴费，2-财务已确认
    private String booking_status;//会议状态 0-未开始，1-使用中，2-已结束，3-已取消
   
    private String meeting_theme;//会议主题
    private String meeting_content;//会议内容500
    private String main_guest;//主要嘉宾
    private String organizers;//主办方
    private String p_name;//联系人姓名
    private String p_tel;//联系人电话
    private String p_wechat;//联系人微信
    private String table_card;//桌牌


}

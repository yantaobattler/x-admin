package com.tky.meeting.vo;

import lombok.Data;
import com.tky.common.vo.Page;

import java.util.Date;

@Data
public class BookingDtlQuery extends Page {
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

}

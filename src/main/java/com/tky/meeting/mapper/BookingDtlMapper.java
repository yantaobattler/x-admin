package com.tky.meeting.mapper;

import java.util.List;

import com.tky.meeting.entity.BookingDtl;
import com.tky.meeting.vo.BookingDtlQuery;

public interface BookingDtlMapper {
	
    List<BookingDtl> getBookingDtlList(BookingDtlQuery param);
    
    Long countBookingDtlList(BookingDtlQuery param);
    
    
    void addBookingDtl(BookingDtl bookingdtl);
    
    BookingDtl getBookingDtlById(String id);
    
    void updateBookingDtl(BookingDtl bookingdtl);
    
	void deleteBookingDtlByid(String roomid);
	
//	void chgdisabled(BookingDtl bookingdtl);
	
}

package com.tky.meeting.mapper;

import java.util.List;

import com.tky.meeting.entity.RoomStatus;
import com.tky.meeting.vo.RoomStatusQuery;

public interface RoomStatusMapper {
	
    List<RoomStatus> getRoomStatusList(RoomStatusQuery param);
    
    Long countRoomStatusList(RoomStatusQuery param);
    
    
    void addRoomStatus(RoomStatus roomstatus);
    
//    RoomStatus getRoomStatusById(String id);
    
    void updateRoomStatus(RoomStatus roomstatus);
    
//	void deleteRoomStatusByid(String roomid);
	
//	void chgdisabled(RoomStatus roomstatus);
	
}

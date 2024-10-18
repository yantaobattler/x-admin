package com.tky.meeting.mapper;

import java.util.List;

import com.tky.meeting.entity.MeetingRoom;
import com.tky.meeting.vo.MeetingRoomQuery;

public interface MeetingRoomMapper {
	
    List<MeetingRoom> getMeetingRoomList(MeetingRoomQuery param);
    
    Long countMeetingRoomList(MeetingRoomQuery param);
    
    
    void addMeetingRoom(MeetingRoom meetingroom);
    
    MeetingRoom getMeetingRoomById(String id);
    
    void updateMeetingRoom(MeetingRoom meetingroom);
    
	void deleteMeetingRoomByid(String roomid);
	
	void chgdisabled(MeetingRoom meetingroom);
	
}

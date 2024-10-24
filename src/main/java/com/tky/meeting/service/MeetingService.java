package com.tky.meeting.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.tky.common.vo.Result;
import com.tky.meeting.entity.MeetingRoom;
import com.tky.meeting.entity.RoomStatus;
import com.tky.meeting.vo.MeetingRoomQuery;
import com.tky.meeting.vo.RoomStatusQuery;

public interface MeetingService {

	List<MeetingRoom> getMeetingRoomList(MeetingRoomQuery param);

	Long countMeetingRoomList(MeetingRoomQuery param);

	Result<Object> chgdisabled(String id, boolean disable);

	Result<Object> addMeetingRoom(MeetingRoom meetingroom);

	Map<String, Object> uploadImage(String id, MultipartFile file, HttpServletRequest request);

	MeetingRoom getMeetingRoomByid(String id);

	Result<Object> deleteMeetingRoom(String id);

	Result<Object> editMeetingRoom(MeetingRoom meetingroom);

	List<RoomStatus> getRoomStatusList(RoomStatusQuery param);

	Long countRoomStatusList(RoomStatusQuery param);


	

}

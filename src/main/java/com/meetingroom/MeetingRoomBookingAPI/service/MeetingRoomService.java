package com.meetingroom.MeetingRoomBookingAPI.service;


import com.meetingroom.MeetingRoomBookingAPI.dto.CreateMeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.MeetingRoomDto;

import java.util.List;
import java.util.UUID;

public interface MeetingRoomService {
    List<MeetingRoomDto> findAllMeetingRooms();
    MeetingRoomDto findMeetingRoomById(UUID id);
    MeetingRoomDto createMeetingRoom(CreateMeetingRoomDto createMeetingRoomDto);
}


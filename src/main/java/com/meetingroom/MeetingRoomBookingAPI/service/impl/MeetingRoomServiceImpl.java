package com.meetingroom.MeetingRoomBookingAPI.service.impl;

import com.meetingroom.MeetingRoomBookingAPI.dto.CreateMeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.MeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.service.MeetingRoomService;

import java.util.List;
import java.util.UUID;

public class MeetingRoomServiceImpl implements MeetingRoomService {
    @Override
    public List<MeetingRoomDto> findAllMeetingRooms() {
        return List.of();
    }

    @Override
    public MeetingRoomDto findMeetingRoomById(UUID id) {
        return null;
    }

    @Override
    public MeetingRoomDto createMeetingRoom(CreateMeetingRoomDto createMeetingRoomDto) {
        return null;
    }
}

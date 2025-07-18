package com.meetingroom.MeetingRoomBookingAPI.mapper;

import com.meetingroom.MeetingRoomBookingAPI.dto.*;
import com.meetingroom.MeetingRoomBookingAPI.models.*;

public class MeetingRoomMapper {
    public static MeetingRoomDto mapToMeetingRoomDto(MeetingRoom meetingRoom) {
        return new MeetingRoomDto(
                meetingRoom.getId(),
                meetingRoom.getName()
        );
    }
    public static MeetingRoom mapToMeetingRoom(MeetingRoomDto meetingRoomDto) {
        return new MeetingRoom(
                meetingRoomDto.getId(),
                meetingRoomDto.getName()
        );
    }
}


package com.meetingroom.MeetingRoomBookingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoomDto {
    private UUID id;
    private String name;
}


package com.meetingroom.MeetingRoomBookingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private UUID id;
    private MeetingRoomDto meetingRoom;
    private LocalDateTime start;
    private LocalDateTime end;
}


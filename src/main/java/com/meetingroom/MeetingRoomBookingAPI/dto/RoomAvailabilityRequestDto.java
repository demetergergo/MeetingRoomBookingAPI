package com.meetingroom.MeetingRoomBookingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomAvailabilityRequestDto {
    private UUID meetingRoomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}


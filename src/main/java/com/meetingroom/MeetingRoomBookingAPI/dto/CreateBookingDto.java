package com.meetingroom.MeetingRoomBookingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDto {
    private String meetingRoomName;
    private LocalDateTime start;
    private LocalDateTime end;
}


package com.meetingroom.MeetingRoomBookingAPI.service;


import com.meetingroom.MeetingRoomBookingAPI.dto.BookingDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.CreateBookingDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.MeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.RoomAvailabilityRequestDto;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    List<BookingDto> findAllBookings();
    BookingDto findBookingById(UUID id);
    BookingDto createBooking(CreateBookingDto createBookingDto, MeetingRoomDto meetingRoomDto);
    Boolean IsRoomAvailable(RoomAvailabilityRequestDto roomAvailabilityRequestDto);
    List<BookingDto> findBookingsByMeetingRoomName(String meetingRoomName);
}

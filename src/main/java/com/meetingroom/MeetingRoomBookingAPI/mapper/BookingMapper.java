package com.meetingroom.MeetingRoomBookingAPI.mapper;

import com.meetingroom.MeetingRoomBookingAPI.dto.*;
import com.meetingroom.MeetingRoomBookingAPI.models.*;

public class BookingMapper {
    public static BookingDto mapToBookingDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                MeetingRoomMapper.mapToMeetingRoomDto(booking.getMeetingRoom()),
                booking.getStartTime(),
                booking.getEndTime()
        );
    }
    public static Booking mapToBooking(BookingDto bookingDto) {
        return new Booking(
                bookingDto.getId(),
                bookingDto.getStart(),
                bookingDto.getEnd(),
                MeetingRoomMapper.mapToMeetingRoom(bookingDto.getMeetingRoom())
        );
    }
}


package com.meetingroom.MeetingRoomBookingAPI.controller;

import com.meetingroom.MeetingRoomBookingAPI.dto.BookingDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.CreateBookingDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.MeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.RoomAvailabilityRequestDto;
import com.meetingroom.MeetingRoomBookingAPI.service.BookingService;
import com.meetingroom.MeetingRoomBookingAPI.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final MeetingRoomService meetingRoomService;

    public BookingController(BookingService bookingService, MeetingRoomService meetingRoomService) {
        this.bookingService = bookingService;
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookingDto> getAllBookings() {
        return bookingService.findAllBookings();
    }

    @GetMapping("/room/{roomName}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingDto> getBookingsByRoom(@PathVariable String roomName) {
        return bookingService.findBookingsByMeetingRoomName(roomName);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookingDto getBookingById(@PathVariable UUID id) {
        return bookingService.findBookingById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingDto createBooking(@RequestBody CreateBookingDto createBookingDto) {
        MeetingRoomDto meetingRoomDto = meetingRoomService.findMeetingRoomByName(createBookingDto.getMeetingRoomName());
        return bookingService.createBooking(createBookingDto, meetingRoomDto);
    }

    @PostMapping("/availability")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isRoomAvailable(@RequestBody RoomAvailabilityRequestDto requestDto) {
        return bookingService.IsRoomAvailable(requestDto);
    }
}

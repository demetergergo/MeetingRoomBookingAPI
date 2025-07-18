package com.meetingroom.MeetingRoomBookingAPI.service.impl;

import com.meetingroom.MeetingRoomBookingAPI.dto.BookingDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.CreateBookingDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.MeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.RoomAvailabilityRequestDto;
import com.meetingroom.MeetingRoomBookingAPI.mapper.BookingMapper;
import com.meetingroom.MeetingRoomBookingAPI.mapper.MeetingRoomMapper;
import com.meetingroom.MeetingRoomBookingAPI.models.Booking;
import com.meetingroom.MeetingRoomBookingAPI.repository.BookingRepository;
import com.meetingroom.MeetingRoomBookingAPI.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingDto> findAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(BookingMapper::mapToBookingDto).collect(Collectors.toList());
    }

    @Override
    public BookingDto findBookingById(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return BookingMapper.mapToBookingDto(booking);
    }

    @Override
    public BookingDto createBooking(CreateBookingDto createBookingDto, MeetingRoomDto meetingRoomDto) {
        if (createBookingDto.getStart().isAfter(createBookingDto.getEnd()) || createBookingDto.getStart().isEqual(createBookingDto.getEnd())) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        if (!bookingRepository.findOverlappingBookings(meetingRoomDto.getId(), createBookingDto.getStart(), createBookingDto.getEnd()).isEmpty()) {
            throw new IllegalStateException("The meeting room is already booked for the given interval.");
        }

        Booking booking = BookingMapper.mapToBooking(createBookingDto, MeetingRoomMapper.mapToMeetingRoom(meetingRoomDto));
        bookingRepository.save(booking);
        Booking savedBooking = bookingRepository.findById(booking.getId())
                .orElseThrow(() -> new RuntimeException("Booking could not be saved"));

        return BookingMapper.mapToBookingDto(savedBooking);
    }

    @Override
    public Boolean IsRoomAvailable(RoomAvailabilityRequestDto roomAvailabilityRequestDto) {
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(
                roomAvailabilityRequestDto.getMeetingRoomId(),
                roomAvailabilityRequestDto.getStartTime(),
                roomAvailabilityRequestDto.getEndTime()
        );
        return overlappingBookings.isEmpty();
    }

    @Override
    public List<BookingDto> findBookingsByMeetingRoomName(String meetingRoomName) {
        List<Booking> bookings = bookingRepository.findByMeetingRoomName(meetingRoomName);
        return bookings.stream().map(BookingMapper::mapToBookingDto).collect(Collectors.toList());
    }

}

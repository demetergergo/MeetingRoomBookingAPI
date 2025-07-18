package com.meetingroom.MeetingRoomBookingAPI.service.impl;

import com.meetingroom.MeetingRoomBookingAPI.config.MockConfig;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private final MockConfig mockConfig;

    private BookingDto mockBooking;

    public BookingServiceImpl(BookingRepository bookingRepository, MockConfig mockConfig) {
        this.bookingRepository = bookingRepository;
        this.mockConfig = mockConfig;

        if (mockConfig.isEnabled()) {
            this.mockBooking = createMockBooking();
        }
    }

    @Override
    public List<BookingDto> findAllBookings() {
        if (mockConfig.isEnabled()) {
            BookingDto mockBooking = createMockBooking();
            return List.of(mockBooking);
        }
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(BookingMapper::mapToBookingDto).collect(Collectors.toList());
    }

    @Override
    public BookingDto findBookingById(UUID id) {
        if (mockConfig.isEnabled()) {
            return mockBooking;
        }
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return BookingMapper.mapToBookingDto(booking);
    }

    @Override
    public BookingDto createBooking(CreateBookingDto createBookingDto, MeetingRoomDto meetingRoomDto) {
        if (mockConfig.isEnabled()) {
            return mockBooking;
        }
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
        if (mockConfig.isEnabled()) {
            return true;
        }
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(
                roomAvailabilityRequestDto.getMeetingRoomId(),
                roomAvailabilityRequestDto.getStartTime(),
                roomAvailabilityRequestDto.getEndTime()
        );
        return overlappingBookings.isEmpty();
    }

    @Override
    public List<BookingDto> findBookingsByMeetingRoomName(String meetingRoomName) {
        if (mockConfig.isEnabled()) {
            return List.of(mockBooking);
        }
        List<Booking> bookings = bookingRepository.findByMeetingRoomName(meetingRoomName);
        return bookings.stream().map(BookingMapper::mapToBookingDto).collect(Collectors.toList());
    }

    private BookingDto createMockBooking() {
        BookingDto mockBooking = new BookingDto();
        mockBooking.setId(new UUID(0, 0));
        mockBooking.setMeetingRoom(new MeetingRoomDto(new UUID(0,0) ,"MockRoom"));
        mockBooking.setStart(java.time.LocalDateTime.of(2025, 1, 1, 10, 0));
        mockBooking.setEnd(java.time.LocalDateTime.of(2025, 1, 1, 11, 0));
        return mockBooking;
    }
}

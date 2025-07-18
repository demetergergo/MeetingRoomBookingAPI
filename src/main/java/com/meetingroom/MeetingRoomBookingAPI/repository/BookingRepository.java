package com.meetingroom.MeetingRoomBookingAPI.repository;

import com.meetingroom.MeetingRoomBookingAPI.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByMeetingRoomName(String roomName);

    @Query("SELECT b FROM Booking b WHERE b.meetingRoom.id = :roomId AND b.startTime < :endTime AND b.endTime > :startTime")
    List<Booking> findOverlappingBookings(UUID roomId, LocalDateTime startTime, LocalDateTime endTime);
}

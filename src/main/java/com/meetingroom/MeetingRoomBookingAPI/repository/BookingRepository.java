package com.meetingroom.MeetingRoomBookingAPI.repository;

import com.meetingroom.MeetingRoomBookingAPI.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

}

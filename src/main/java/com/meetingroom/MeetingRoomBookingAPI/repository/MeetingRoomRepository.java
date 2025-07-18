package com.meetingroom.MeetingRoomBookingAPI.repository;

import com.meetingroom.MeetingRoomBookingAPI.models.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, UUID> {
    public MeetingRoom findMeetingRoomByName(String name);
}

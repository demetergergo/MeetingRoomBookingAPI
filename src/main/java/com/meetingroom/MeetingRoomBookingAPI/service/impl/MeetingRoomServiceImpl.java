package com.meetingroom.MeetingRoomBookingAPI.service.impl;

import com.meetingroom.MeetingRoomBookingAPI.dto.CreateMeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.MeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.mapper.MeetingRoomMapper;
import com.meetingroom.MeetingRoomBookingAPI.models.MeetingRoom;
import com.meetingroom.MeetingRoomBookingAPI.repository.MeetingRoomRepository;
import com.meetingroom.MeetingRoomBookingAPI.service.MeetingRoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

    private static MeetingRoomRepository meetingRoomRepository;

    public MeetingRoomServiceImpl(MeetingRoomRepository meetingRoomRepository) {
        MeetingRoomServiceImpl.meetingRoomRepository = meetingRoomRepository;
    }

    @Override
    public List<MeetingRoomDto> findAllMeetingRooms() {
        List<MeetingRoom> meetingRooms = meetingRoomRepository.findAll();
        return meetingRooms.stream().map(MeetingRoomMapper::mapToMeetingRoomDto).collect(Collectors.toList());
    }

    @Override
    public MeetingRoomDto findMeetingRoomByName(String name) {
        MeetingRoom meetingRoom = meetingRoomRepository.findMeetingRoomByName(name);
        return MeetingRoomMapper.mapToMeetingRoomDto(meetingRoom);
    }

    @Override
    public MeetingRoomDto createMeetingRoom(CreateMeetingRoomDto createMeetingRoomDto) {
        MeetingRoom meetingRoom = MeetingRoomMapper.mapToMeetingRoom(createMeetingRoomDto);
        meetingRoomRepository.save(meetingRoom);

        MeetingRoom savedMeetingRoom = meetingRoomRepository.findById(meetingRoom.getId())
                .orElseThrow(() -> new RuntimeException("Meeting room could not be saved"));

        return MeetingRoomMapper.mapToMeetingRoomDto(savedMeetingRoom);
    }
}

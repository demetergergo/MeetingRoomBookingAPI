package com.meetingroom.MeetingRoomBookingAPI.service.impl;

import com.meetingroom.MeetingRoomBookingAPI.config.MockConfig;
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

    private final MockConfig mockConfig;

    private MeetingRoomDto mockMeetingRoomDto;

    public MeetingRoomServiceImpl(MeetingRoomRepository meetingRoomRepository, MockConfig mockConfig) {
        MeetingRoomServiceImpl.meetingRoomRepository = meetingRoomRepository;
        this.mockConfig = mockConfig;

        if (mockConfig.isEnabled()) {
            this.mockMeetingRoomDto = createMockMeetingRoomDto();
        }
    }

    @Override
    public List<MeetingRoomDto> findAllMeetingRooms() {
        if (mockConfig.isEnabled()) {
            return List.of(mockMeetingRoomDto);
        }
        List<MeetingRoom> meetingRooms = meetingRoomRepository.findAll();
        return meetingRooms.stream().map(MeetingRoomMapper::mapToMeetingRoomDto).collect(Collectors.toList());
    }

    @Override
    public MeetingRoomDto findMeetingRoomByName(String name) {
        if (mockConfig.isEnabled()) {
            return mockMeetingRoomDto;
        }
        MeetingRoom meetingRoom = meetingRoomRepository.findMeetingRoomByName(name);
        if (meetingRoom == null) {
            throw new IllegalArgumentException("Meeting room: '" + name + "' not found.");
        }
        return MeetingRoomMapper.mapToMeetingRoomDto(meetingRoom);
    }

    @Override
    public MeetingRoomDto createMeetingRoom(CreateMeetingRoomDto createMeetingRoomDto) {
        if (mockConfig.isEnabled()) {
            return mockMeetingRoomDto;
        }
        MeetingRoom existing = meetingRoomRepository.findMeetingRoomByName(createMeetingRoomDto.getName());
        if (existing != null) {
            throw new IllegalArgumentException("Meeting room name must be unique.");
        }
        MeetingRoom meetingRoom = MeetingRoomMapper.mapToMeetingRoom(createMeetingRoomDto);
        meetingRoomRepository.save(meetingRoom);

        MeetingRoom savedMeetingRoom = meetingRoomRepository.findById(meetingRoom.getId())
                .orElseThrow(() -> new RuntimeException("Meeting room could not be saved"));

        return MeetingRoomMapper.mapToMeetingRoomDto(savedMeetingRoom);
    }

    private MeetingRoomDto createMockMeetingRoomDto() {
        MeetingRoomDto mockRoom = new MeetingRoomDto();
        mockRoom.setId(new UUID(0,0));
        mockRoom.setName("MockRoom");
        return mockRoom;
    }
}

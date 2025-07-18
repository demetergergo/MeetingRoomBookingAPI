package com.meetingroom.MeetingRoomBookingAPI.controller;

import com.meetingroom.MeetingRoomBookingAPI.dto.CreateMeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.dto.MeetingRoomDto;
import com.meetingroom.MeetingRoomBookingAPI.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/meeting-rooms")
public class MeetingRoomController {
    private final MeetingRoomService meetingRoomService;


    public MeetingRoomController(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MeetingRoomDto> getAllMeetingRooms() {
        return meetingRoomService.findAllMeetingRooms();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingRoomDto getMeetingRoomByName(@PathVariable String name) {
        return meetingRoomService.findMeetingRoomByName(name);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MeetingRoomDto createMeetingRoom(@RequestBody CreateMeetingRoomDto createMeetingRoomDto) {
        return meetingRoomService.createMeetingRoom(createMeetingRoomDto);
    }
}

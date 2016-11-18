package com.hackergames.rooms.service;

import com.hackergames.rooms.model.Room;

import java.util.HashSet;

public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Override
    public Long createNewRoom() {
        Room room = new Room(new HashSet<>());
        roomRepository.save(room);
        return room.getId();
    }

    @Override
    public void deleteRoom(Long roomID) {
        roomRepository.delete(roomID);
    }
}

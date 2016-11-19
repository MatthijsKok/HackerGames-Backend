package com.hackergames.rooms.service;

import com.hackergames.rooms.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createNewRoom() {
        Room room = new Room(new ArrayList<>());
        roomRepository.save(room);
        return room;
    }

    @Override
    public Room getRoom(Long roomID) {
        return roomRepository.getOne(roomID);
    }

    @Override
    public void deleteRoom(Long roomID) {
        roomRepository.delete(roomID);
    }
}

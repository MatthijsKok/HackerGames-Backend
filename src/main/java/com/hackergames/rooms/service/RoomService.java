package com.hackergames.rooms.service;

import com.hackergames.rooms.model.Room;

public interface RoomService {

    Room createNewRoom();

    Room getRoom(Long roomID);

    void deleteRoom(Long roomID);

}

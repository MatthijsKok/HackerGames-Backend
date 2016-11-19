package com.hackergames.rooms.service;

import com.hackergames.pizzas.model.Pizza;
import com.hackergames.rooms.model.Room;

public interface RoomService {

    Room createNewRoom();

    Room getRoom(Long roomID);

    void deleteRoom(Long roomID);

    Pizza addPizza(Long roomID, Pizza pizza);

    void deletePizza(Long roomID, Pizza pizza);

}

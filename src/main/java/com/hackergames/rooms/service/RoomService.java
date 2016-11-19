package com.hackergames.rooms.service;

import com.hackergames.pizzas.model.Pizza;
import com.hackergames.rooms.model.Room;

import java.util.List;

public interface RoomService {

    Room createNewRoom();

    Room getRoom(Long roomID);

    void deleteRoom(Long roomID);

    void deleteAllRooms();

    Pizza addPizza(Long roomID, Pizza pizza);

    void deletePizza(Long roomID, Pizza pizza);

    List<Pizza> getAllPizzas(Long roomID);

}

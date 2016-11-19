package com.hackergames.rooms.service;

import com.hackergames.pizzas.model.Pizza;
import com.hackergames.rooms.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return roomRepository.findOne(roomID);
    }

    @Override
    public void deleteRoom(Long roomID) {
        roomRepository.delete(roomID);
    }

    @Override
    public void deleteAllRooms() {
        roomRepository.deleteAll();
    }

    @Override
    public Pizza addPizza(Long roomID, Pizza pizza) {
        roomRepository.findOne(roomID).getPizzas().add(pizza);
        return pizza;
    }

    @Override
    public void deletePizza(Long roomID, Pizza pizza) {
        roomRepository.findOne(roomID).getPizzas().remove(pizza);
    }

    @Override
    public List<Pizza> getAllPizzas(Long roomID) {
        return roomRepository.findOne(roomID).getPizzas();
    }
}

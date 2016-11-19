package com.hackergames.rooms.controller;

import com.hackergames.pizzas.model.Pizza;
import com.hackergames.rooms.model.Room;
import com.hackergames.rooms.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RoomRestController {

    private RoomServiceImpl roomService;

    @Autowired
    public RoomRestController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/rooms")
    public Room createRoom() {
        return roomService.createNewRoom();
    }

    @DeleteMapping("/rooms")
    public void deleteAllRooms() {
        roomService.deleteAllRooms();
    }


    @DeleteMapping("/room/{roomID}")
    public void deleteRoom(@PathVariable Long roomID) {
        roomService.deleteRoom(roomID);
    }

    @GetMapping("/room/{roomID}")
    public Room getRoom(@PathVariable Long roomID) {
        return roomService.getRoom(roomID);
    }


    @PostMapping("/room/{roomID}/pizza")
    public Pizza addPizza(@PathVariable Long roomID, @RequestBody Pizza pizza) {
        roomService.addPizza(roomID, pizza);
        return pizza;
    }

    @DeleteMapping("/room/{roomID}/pizza")
    public void deletePizza(@PathVariable Long roomID, @RequestBody Pizza pizza) {
        roomService.deletePizza(roomID, pizza);
    }
}

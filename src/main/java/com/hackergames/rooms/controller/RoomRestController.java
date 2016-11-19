package com.hackergames.rooms.controller;

import com.hackergames.pizzas.model.Pizza;
import com.hackergames.rooms.model.Room;
import com.hackergames.rooms.service.RoomServiceImpl;
import com.hackergames.util.ResponseEntityBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


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

    @PutMapping("/room/{roomID}/pizza/{name:.+}&{size:.+}")
    public ResponseEntity<?> addPizza(@PathVariable Long roomID, @PathVariable String name,
                                      @PathVariable String size) {
        Pizza pizza = Pizza.fromName(name, size, new ArrayList<>());
        if (pizza == null) {
            return ResponseEntityBuilder.createResponseEntity(HttpStatus.BAD_REQUEST, "Invalid pizza name");
        }

        roomService.addPizza(roomID, pizza);
        return ResponseEntityBuilder.createResponseEntity(HttpStatus.OK, JSONObject.wrap(pizza).toString());
    }

    @PutMapping("/room/{roomID}/pizza/{name:.+}&{size:.+}&{additions:.+}")
    public ResponseEntity<?> addPizza(@PathVariable Long roomID, @PathVariable String name,
                                      @PathVariable String size, @PathVariable ArrayList<String> additions) {
        Pizza pizza = Pizza.fromName(name, size, additions);
        if (pizza == null) {
            return ResponseEntityBuilder.createResponseEntity(HttpStatus.BAD_REQUEST, "Invalid pizza name");
        }

        roomService.addPizza(roomID, pizza);
        return ResponseEntityBuilder.createResponseEntity(HttpStatus.OK, JSONObject.wrap(pizza).toString());
    }

    @DeleteMapping("/room/{roomID}/pizza/{name:.+}&{size:.+}")
    public ResponseEntity<?> deletePizza(@PathVariable Long roomID, @PathVariable String name,
                                         @PathVariable String size) {
        Pizza pizza = Pizza.fromName(name, size, new ArrayList<>());
        if (pizza == null) {
            return ResponseEntityBuilder.createResponseEntity(HttpStatus.BAD_REQUEST, "Invalid pizza name");
        }

        roomService.deletePizza(roomID, pizza);
        return ResponseEntityBuilder.createResponseEntity(HttpStatus.OK, "Pizza deleted successfully");
    }

    @DeleteMapping("/room/{roomID}/pizza/{name:.+}&{size:.+}&{additions:.+}")
    public ResponseEntity<?> deletePizza(@PathVariable Long roomID, @PathVariable String name,
                                         @PathVariable String size, @PathVariable ArrayList<String> additions) {
        Pizza pizza = Pizza.fromName(name, size, additions);
        if (pizza == null) {
            return ResponseEntityBuilder.createResponseEntity(HttpStatus.BAD_REQUEST, "Invalid pizza name");
        }

        roomService.deletePizza(roomID, pizza);
        return ResponseEntityBuilder.createResponseEntity(HttpStatus.OK, "Pizza deleted successfully");
    }
}

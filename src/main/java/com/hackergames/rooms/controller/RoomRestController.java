package com.hackergames.rooms.controller;

import com.hackergames.pizzas.model.Pizza;
import com.hackergames.rooms.model.Room;
import com.hackergames.rooms.service.RoomServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomRestController {

    private RoomServiceImpl roomService;

    @Autowired
    public RoomRestController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/new")
    public Room createRoom() {
        return roomService.createNewRoom();
    }

    @DeleteMapping("/{roomID}")
    public void deleteRoom(@PathVariable Long roomID) {
        roomService.deleteRoom(roomID);
    }

    @GetMapping("/{roomID}")
    public Room getRoom(@PathVariable Long roomID) {
        return roomService.getRoom(roomID);
    }

    @GetMapping("/{roomID}/add_pizza/{name}&{size}&{additions}")
    public String addPizza(@PathVariable Long roomID, @PathVariable String name, @PathVariable String size,
                          @PathVariable ArrayList<String> additions) {
        Pizza pizza = Pizza.fromName(name, size, additions);
        roomService.addPizza(roomID, pizza);
        return JSONObject.wrap(pizza).toString();
    }

    @GetMapping("/{roomID}/delete_pizza/{pizza}&{size}&{additions}")
    public void deletePizza(@PathVariable Long roomID, @PathVariable String pizzaName, @PathVariable String pizzaSize,
                            @PathVariable ArrayList<String> pizzaAdditions) {
        Pizza pizza = Pizza.fromName(pizzaName, pizzaSize, pizzaAdditions);
        roomService.deletePizza(roomID, pizza);
    }

    @GetMapping("/{roomID/all}")
    public List<Pizza> getAllPizzas(@PathVariable Long roomID) {
        return roomService.getAllPizzas(roomID);
    }
}

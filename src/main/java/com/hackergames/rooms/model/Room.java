package com.hackergames.rooms.model;


import com.hackergames.pizzas.model.Pizza;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Room {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    public ArrayList<Pizza> pizzas;


    public Room(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

}

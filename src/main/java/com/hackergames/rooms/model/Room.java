package com.hackergames.rooms.model;


import com.hackergames.pizzas.model.Pizza;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

public class Room {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    public Set<Pizza> pizzas;

    public Room(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

}

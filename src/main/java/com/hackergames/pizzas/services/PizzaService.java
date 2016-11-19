package com.hackergames.pizzas.services;

import com.hackergames.pizzas.model.PizzaOptions;

import java.util.List;


/**
 * Interface for the pizza service.
 */
public interface PizzaService {
    /**
     * Returns a list of all pizzas.
     *
     * @return a list of all pizzas
     */
    List<PizzaOptions> getAllPizzas();
}

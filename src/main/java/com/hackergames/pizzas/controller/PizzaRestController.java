package com.hackergames.pizzas.controller;

import com.hackergames.pizzas.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Controller for the pizzas.
 */
@RestController
@RequestMapping("/pizza")
public class PizzaRestController
{
    private final PizzaService pizzaService;


    @Autowired
    public PizzaRestController(final PizzaService pizzaService)
    {
        this.pizzaService = pizzaService;
    }


    @GetMapping("/all")
    public List<Object> getAllPizzas()
    {
        return PizzaService.getAllPizzas();
    }
}
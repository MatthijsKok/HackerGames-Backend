package com.hackergames.pizzas.controller;

import com.hackergames.pizzas.model.PizzaOptions;
import com.hackergames.pizzas.services.FakePizzaService;
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
    private final FakePizzaService pizzaService;


    @Autowired
    public PizzaRestController(FakePizzaService pizzaService)
    {
        this.pizzaService = pizzaService;
    }


    @GetMapping("/all")
    public List<PizzaOptions> getAllPizzas()
    {
        return pizzaService.getAllPizzas();
    }
}

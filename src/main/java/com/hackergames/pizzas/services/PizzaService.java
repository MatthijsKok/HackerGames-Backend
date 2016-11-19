package com.hackergames.pizzas.services;

import com.hackergames.pizzas.model.OrderInfo;
import com.hackergames.pizzas.model.Pizza;
import com.hackergames.pizzas.model.PizzaOptions;

import java.util.List;


/**
 * Interface for the pizza service.
 */
public interface PizzaService
{
    /**
     * Returns a list of all pizzas.
     *
     * @return a list of all pizzas
     */
    List<PizzaOptions> getAllPizzas();

    /**
     * Places an order and returns its id.
     *
     * @param pizzas the list of pizzas to order
     * @return the id of the order
     */
    String placeOrder(final List<Pizza> pizzas);

    /**
     * Returns the information about an order.
     *
     * @param orderId the id of the order
     * @return the information about the order
     */
    OrderInfo getOrderInfo(final String orderId);
}

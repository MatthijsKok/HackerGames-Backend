package com.hackergames.pizzas.services;

import com.hackergames.pizzas.model.Order;

import java.util.ArrayList;
import java.util.List;


public class PizzaService
{
    private static List<Order> orders = new ArrayList<>();


    public static List<Object> getAllPizzas()
    {
        return null;
    }

    public Order placeOrder(final List<Object> pizzas)
    {
        final Order order = new Order(orders.size(), Order.Status.Queued, "", 30 * 60000, System.currentTimeMillis());
        orders.add(order);
        return order;
    }

    public Order getOrderInfo(final int orderId)
    {
        final Order order = orders.get(orderId);
        order.update();
        return order;
    }

    public Order getOrderInfo(final Order order)
    {
        order.update();
        return order;
    }
}

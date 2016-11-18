package com.hackergames.api;

import java.util.ArrayList;
import java.util.List;


public class ApiInterface
{
    private static List<Order> orders = new ArrayList<>();


    static Order placeOrder(final List<Object> pizzas)
    {
        final Order order = new Order(orders.size(), Order.Status.Queued, "", 30 * 60000, System.currentTimeMillis());
        orders.add(order);
        return order;
    }

    static Order getOrderInfo(final int orderId)
    {
        final Order order = orders.get(orderId);
        order.update();
        return order;
    }

    static Order getOrderInfo(final Order order)
    {
        order.update();
        return order;
    }
}

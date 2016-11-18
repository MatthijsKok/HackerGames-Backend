package com.hackergames.api;

import java.util.ArrayList;
import java.util.List;


public class ApiInterface
{
    private static List<Order> orders = new ArrayList<>();


    /**
     * UNTESTED.
     *
     * @param storeId
     * @param isCashPayment
     * @param name
     * @param phoneNumber
     * @param vendorId
     * @param products
     * @return the order's id
     */
    static Order placeOrder(final List<Object> pizzas)
    {
        final Order order = new Order(orders.size(), "queue", "", 30, System.currentTimeMillis());
        orders.add(order);
        return order;
    }

    /**
     * UNTESTED.
     *
     * @param vendorId
     * @param order
     * @return
     */
    static Order getOrderInfo(final int orderId)
    {
        final Order order = orders.get(orderId);
        order.updateEta();
        return order;
    }
}

package com.hackergames.pizzas.model;


/**
 * Contains information about an order.
 */
public final class OrderInfo
{
    private final String orderStatus;
    private final String orderId;
    private final String additionalInfo;


    /**
     * Constructs a new {@code OrderInfo}.
     *
     * @param orderStatus    the status of the order
     * @param orderId        the id of the order
     * @param additionalInfo additional info on the order
     */
    public OrderInfo(final String orderStatus, final String orderId, final String additionalInfo)
    {
        this.orderStatus = orderStatus;
        this.orderId = orderId;
        this.additionalInfo = additionalInfo;
    }


    public String getOrderStatus()
    {
        return orderStatus;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }
}

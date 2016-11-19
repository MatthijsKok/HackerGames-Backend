package com.hackergames.pizzas.services;

import com.hackergames.pizzas.model.Order;
import com.hackergames.pizzas.model.OrderInfo;
import com.hackergames.pizzas.model.Pizza;
import com.hackergames.pizzas.model.PizzaOptions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Fake pizza service simulating Domino's.
 */
@Service
public final class FakePizzaService implements PizzaService
{
    private static List<Order> orders = new ArrayList<>();


    @Autowired
    public FakePizzaService()
    {
    }


    @Override
    public List<PizzaOptions> getAllPizzas()
    {
        final List<PizzaOptions> pizzas = new ArrayList<>();

        try
        {
            final JSONObject menu = Requester.getRequest("https://hackathon-menu.dominos.cloud/", "/Rest/nl/menus/30544/en");
            final JSONArray jPizzas = menu.getJSONArray("MenuPages").getJSONObject(0).getJSONArray("SubMenus");
            for (int i = 0; i < jPizzas.length(); i++)
            {
                final JSONObject subMenu = jPizzas.getJSONObject(i);
                final JSONArray jProducts = subMenu.getJSONArray("Products");

                for (int j = 0; j < jProducts.length(); j++)
                {
                    pizzas.add(PizzaOptions.fromJson(jProducts.getJSONObject(i)));
                }
            }
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }

        return pizzas;
    }

    @Override
    public String placeOrder(final List<Pizza> pizzas)
    {
        final Order order = new Order(orders.size(), Order.Status.Queued, "", 30 * 60000, System.currentTimeMillis());
        orders.add(order);
        return Integer.toString(order.getId());
    }

    @Override
    public OrderInfo getOrderInfo(final String orderId)
    {
        final Order order = orders.get(Integer.parseInt(orderId));
        order.update();
        return new OrderInfo(order.getStatus().toString(), Integer.toString(order.getId()), order.getInfo());
    }
}

package com.hackergames.pizzas.services;

import com.hackergames.pizzas.model.OrderInfo;
import com.hackergames.pizzas.model.Pizza;
import com.hackergames.pizzas.model.PizzaOptions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Real pizza service using the Domino's API.
 */
@Service
public final class RealPizzaService implements PizzaService
{
    private static final String DEFAULT_COUNTRY_CODE = "NL";
    private static final String DEFAULT_VENDOR_ID = "36040d87-684e-4aee-9e03-3f4e17010b26";


    @Autowired
    public RealPizzaService()
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
        final JSONObject jAddress = new JSONObject();
        jAddress.put("StreetNo", "7");
        jAddress.put("StreetName", "Westkanaaldijk");
        jAddress.put("Suburb", "Maarssen");
        jAddress.put("PostalCode", "3542DA");
        jAddress.put("DeliveryInstruction", "");
        final JSONArray jPizzas = new JSONArray();
        pizzas.forEach(jPizzas::put);

        final JSONObject parameters = new JSONObject();
        parameters.put("StoreNo", ""); // what is this?
        parameters.put("CountryCode", DEFAULT_COUNTRY_CODE);
        parameters.put("Language", DEFAULT_COUNTRY_CODE);
        parameters.put("OrderDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"));
        parameters.put("IsCashPayment", true);
        parameters.put("Name", "Hackathon");
        parameters.put("PhoneNumber", "0612345678");
        parameters.put("VendorId", DEFAULT_VENDOR_ID);
        parameters.put("DeliverTo", jAddress);
        parameters.put("Products", jPizzas);

        JSONObject response;
        try
        {
            response = new JSONObject(Requester.postRequest("/order/place", parameters));
        }
        catch (final IOException e)
        {
            e.printStackTrace();
            response = new JSONObject();
        }
        return response.getString("OrderId");
    }

    @Override
    public OrderInfo getOrderInfo(final String orderId)
    {
        final JSONObject parameters = new JSONObject();
        parameters.put("CountryCode", DEFAULT_COUNTRY_CODE);
        parameters.put("VendorId", DEFAULT_VENDOR_ID);
        parameters.put("OrderId", orderId);

        JSONObject response;
        try
        {
            response = new JSONObject(Requester.postRequest("/order/status", parameters));
        }
        catch (final IOException e)
        {
            e.printStackTrace();
            return null;
        }
        return new OrderInfo(
                response.getString("OrderStatus"),
                response.getString("OrderId"),
                response.getString("AdditionalInfo")
        );
    }
}

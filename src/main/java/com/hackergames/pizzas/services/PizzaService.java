package com.hackergames.pizzas.services;

import com.hackergames.pizzas.model.Order;
import com.hackergames.pizzas.model.PizzaOptions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PizzaService
{
    private static final String DEFAULT_COUNTRY_CODE = "NL";
    private static final String DEFAULT_VENDOR_ID = "36040d87-684e-4aee-9e03-3f4e17010b26";
    private static final String URL_BASE = "https://hackathon.dominos.cloud/";

    private static List<Order> orders = new ArrayList<>(); // TODO remove this sometime


    /**
     * Returns a list of all pizzas.
     *
     * @return a list of all pizzas
     */
    public static List<PizzaOptions> getAllPizzas()
    {
        final List<PizzaOptions> pizzas = new ArrayList<>();

        try
        {
            final JSONObject menu = getRequest("https://hackathon-menu.dominos.cloud/", "/Rest/nl/menus/30544/en");
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

//    /**
//     * Places an order and returns its id.
//     *
//     * @param pizzas the list of pizzas to order
//     * @return the id of the order
//     */
//    public String placeOrder(final List<Pizza> pizzas)
//    {
//        final JSONObject jAddress = new JSONObject();
//        jAddress.put("StreetNo", "7");
//        jAddress.put("StreetName", "Westkanaaldijk");
//        jAddress.put("Suburb", "Maarssen");
//        jAddress.put("PostalCode", "3542DA");
//        jAddress.put("DeliveryInstruction", "");
//        final JSONArray jPizzas = new JSONArray();
//        pizzas.forEach(jPizzas::put);
//
//        final JSONObject parameters = new JSONObject();
//        parameters.put("StoreNo", ""); // what is this?
//        parameters.put("CountryCode", DEFAULT_COUNTRY_CODE);
//        parameters.put("Language", DEFAULT_COUNTRY_CODE);
//        parameters.put("OrderDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"));
//        parameters.put("IsCashPayment", true);
//        parameters.put("Name", "Hackathon");
//        parameters.put("PhoneNumber", "0612345678");
//        parameters.put("VendorId", DEFAULT_VENDOR_ID);
//        parameters.put("DeliverTo", jAddress);
//        parameters.put("Products", jPizzas);
//
//        JSONObject response;
//        try
//        {
//            response = new JSONObject(postRequest("/order/place", parameters));
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//            response = new JSONObject();
//        }
//        return response.getString("OrderId");
//    }

//    /**
//     * Returns the information about an order.
//     *
//     * @param orderId the id of the order
//     * @return the information about the order
//     */
//    public OrderInfo getOrderInfo(final String orderId)
//    {
//        final JSONObject parameters = new JSONObject();
//        parameters.put("CountryCode", DEFAULT_COUNTRY_CODE);
//        parameters.put("VendorId", DEFAULT_VENDOR_ID);
//        parameters.put("OrderId", orderId);
//
//        JSONObject response;
//        try
//        {
//            response = new JSONObject(postRequest("/order/status", parameters));
//        }
//        catch (final IOException e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//        return new OrderInfo(
//                response.getString("OrderStatus"),
//                response.getString("OrderId"),
//                response.getString("AdditionalInfo")
//        );
//    }

    /**
     * FAKE. Places a fake order.
     *
     * @param pizzas the pizzas (ignored0
     * @return the order id
     */
    // TODO remove method below
    public Order placeOrder(final List<Object> pizzas)
    {
        final Order order = new Order(orders.size(), Order.Status.Queued, "", 30 * 60000, System.currentTimeMillis());
        orders.add(order);
        return order;
    }

    /**
     * FAKE. Returns the information on the given order.
     *
     * @param orderId the id of the order
     * @return the information on the given order
     */
    // TODO remove method below
    public Order getOrderInfo(final int orderId)
    {
        final Order order = orders.get(orderId);
        order.update();
        return order;
    }

    /**
     * FAKE. Returns the information on the given order.
     *
     * @param order the order
     * @return the information on the given order
     */
    // TODO remove method below
    public Order getOrderInfo(final Order order)
    {
        order.update();
        return order;
    }


    /**
     * Executes a GET request on the given endpoint.
     *
     * @param endpoint the Domino's API endpoint
     */
    private static JSONObject getRequest(final String endpoint) throws IOException
    {
        return getRequest(URL_BASE, endpoint);
    }

    /**
     * Executes a GET request on the given endpoint.
     *
     * @param domain   the domain name of the API to use
     * @param endpoint the Domino's API endpoint
     */
    private static JSONObject getRequest(final String domain, final String endpoint) throws IOException
    {
        final URL request = new URL(domain + endpoint);
        final JSONTokener tokener = new JSONTokener(request.openStream());
        return new JSONObject(tokener);
    }

    /**
     * Executes a POST request on the given endpoint, with the given data.
     *
     * @param endpoint   the Domino's API endpoint
     * @param parameters the data to POST
     */
    private static String postRequest(final String endpoint, final JSONObject parameters) throws IOException
    {
        return postRequest(URL_BASE, endpoint, parameters);
    }

    /**
     * Executes a POST request on the given endpoint, with the given data.
     *
     * @param domain     the domain name of the API to use
     * @param endpoint   the Domino's API endpoint
     * @param parameters the data to POST
     */
    private static String postRequest(final String domain, final String endpoint, final JSONObject parameters)
            throws IOException
    {
        final URLConnection connection = new URL(URL_BASE + endpoint).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/json");

        try (OutputStream output = connection.getOutputStream())
        {
            output.write(parameters.toString().getBytes("UTF-8"));
        }

        final InputStream responseStream = connection.getInputStream();
        final Scanner responseScanner = new Scanner(responseStream).useDelimiter("\\A");
        final String response = responseScanner.next();
        responseScanner.close();
        return response;
    }
}

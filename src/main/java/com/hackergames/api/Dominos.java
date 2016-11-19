package com.hackergames.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;


/**
 * Main API interface for Domino's.
 */
public final class Dominos
{
    private static final String DEFAULT_COUNTRY_CODE = "NL";
    private static final String DEFAULT_VENDOR_ID = "36040d87-684e-4aee-9e03-3f4e17010b26";
    private static final String URL_BASE = "https://hackathon.dominos.cloud/";


    /**
     * Places an order and returns its id.
     *
     * @param pizzas the list of pizzas to order
     * @return the id of the order
     */
    public static String placeOrder(final List<Pizza> pizzas)
    {
        final JSONObject jAddress = new JSONObject();
//        jAddress.put("UnitNo", ""); // is optional
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
            response = new JSONObject(postRequest("/order/place", parameters));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            response = new JSONObject();
        }
        return response.getString("OrderId");
    }

    /**
     * Returns the information about an order.
     *
     * @param orderId the id of the order
     * @return the information about the order
     */
    public static OrderInfo getOrderInfo(final String orderId)
    {
        final JSONObject parameters = new JSONObject();
        parameters.put("CountryCode", DEFAULT_COUNTRY_CODE);
        parameters.put("VendorId", DEFAULT_VENDOR_ID);
        parameters.put("OrderId", orderId);

        JSONObject response;
        try
        {
            response = new JSONObject(postRequest("/order/status", parameters));
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


    /**
     * Executes a GET request on the given endpoint.
     *
     * @param endpoint the Domino's API endpoint
     */
    private static JSONObject getRequest(final String endpoint) throws IOException
    {
        final URL request = new URL(URL_BASE + endpoint);
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

package com.hackergames.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ApiInterface
{
    public static void main(String[] args) throws Exception
    {
        placeOrder("30553", true, "Dekker", "0612345678", "49E95A7-8D42-4D6D-B7FC-9D341951999C", new ArrayList<>());
    }

    private static final String DEFAULT_COUNTRY_CODE = "NL";
    private static final String DEFAULT_LANGUAGE = "NL";
    private static final JSONObject DEFAULT_ADDRESS = new JSONObject("{\"UnitNo\":\"14\",\"StreetNo\":\"1190\",\"StreetName\":\"Bisonspoor\",\"Suburb\":\"Maarssen\",\"PostalCode\":\"3605KX\",\"DeliveryInstruction\":\"Yes\"}");


    /**
     * Returns a list of all pizzas in the universe.
     * This function probably works.
     *
     * @return a list of all pizzas in the universe
     */
    static List<Pizza> getAll()
    {
        try
        {
            JSONObject response = new JSONObject(Internet.sendGet("https://hackathon-menu.dominos.cloud/", "/Rest/nl/menus/30544/en"));
            JSONArray pizzaMenu = response.getJSONArray("MenuPages").getJSONObject(0).getJSONArray("SubMenus");

            List<Pizza> pizzas = new ArrayList<>();
            if (pizzaMenu != null)
            {
                for (int i = 0; i < pizzaMenu.length(); i++)
                {
                    String subMenuName = pizzaMenu.getJSONObject(i).getString("Name");
                    JSONArray pizzaSubMenu = pizzaMenu.getJSONObject(i).getJSONArray("Products");
                    for (int j = 0; j < pizzaSubMenu.length(); j++)
                    {
                        pizzas.add(Pizza.fromJson(pizzaSubMenu.getJSONObject(j), subMenuName));
                    }
                }
            }
            return pizzas;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

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
    static String placeOrder(String storeId, boolean isCashPayment, String name, String phoneNumber, String vendorId,
                             List<Product> products)
    {
        JSONObject parameters = new JSONObject();

        try
        {
            parameters.put("StoreNo", storeId);
            parameters.put("CountryCode", DEFAULT_COUNTRY_CODE);
            parameters.put("Language", DEFAULT_LANGUAGE);
            parameters.put("OrderDate", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
            parameters.put("IsCashPayment", isCashPayment);
            parameters.put("Name", name);
            parameters.put("PhoneNumber", phoneNumber);
            parameters.put("VendorId", vendorId);
            parameters.put("DeliverTo", DEFAULT_ADDRESS);
            parameters.put("Products", new JSONArray(
                    "[{\"ProductCode\": \"PQGU\",\"Price\": \"795\",\"SizeCode\": \"Pizza.25CM\",\"Additions\": [\"Topping.B\",\"Topping.B\"]}]")
            );

            System.out.println(parameters);
            JSONObject result = new JSONObject(Internet.sendPost("order/place", parameters));
            System.out.println(result);
            return result.getString("OrderId");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /**
     * UNTESTED.
     *
     * @param vendorId
     * @param order
     * @return
     */
    static Order getOrderInfo(String vendorId, Order order)
    {
        Order orderInfo;
        try
        {
            JSONObject parameters = new JSONObject();
            parameters.put("CountryCode", DEFAULT_COUNTRY_CODE);
            parameters.put("VendorId", vendorId);
            parameters.put("OrderId", order.id);

            JSONObject response = new JSONObject(Internet.sendPost("/order/status", parameters));
            orderInfo = new Order(
                    response.getString("id"),
                    response.getString("status"),
                    response.getString("info")
            );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return order;
    }
}

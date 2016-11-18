package hackergames.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ApiInterface
{
    public static void main(String[] args)
    {
        System.out.println(getOrderInfo("36040d87-684e-4aee-9e03-3f4e17010b26", new Order("9ccf5677-205a-4fb6-8262-c57b398936c0", null, null)));
    }

    private static final String DEFAULT_COUNTRY_CODE = "NL";
    private static final String DEFAULT_LANGUAGE = "Dutch";
    private static final String DEFAULT_ADDRESS = "{}";


    static List<Pizza> getAll()
    {
        JSONObject response = Internet.sendGet("https://hackathon-menu.dominos.cloud/", "/Rest/nl/menus/30544/en");
        try
        {
            JSONArray menu = response.getJSONArray("MenuPages");

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    static boolean pizzaExists(String pizza)
    {
        return false;
    }

    static void placeOrder(String storeId, Order order, boolean isCashPayment, String name, String phoneNumber,
                           String vendorId, List<Product> products)
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
            parameters.put("Products", parameters);

            String result = Internet.sendPost("/order/place", parameters);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

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

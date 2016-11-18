//package hackergames.api;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//
//public class ApiInterface
//{
//    private static final String DEFAULT_COUNTRY_CODE = "NL";
//    private static final String DEFAULT_LANGUAGE = "Dutch";
//    private static final String DEFAULT_ADDRESS = "{}";
//
//
//    List<Pizza> getAll()
//    {
//        //
//    }
//
//    boolean pizzaExists(String pizza)
//    {
//        //
//    }
//
//    void placeOrder(String storeId, Order order, boolean isCashPayment, String name, String phoneNumber, String vendorId,
//                    List<Product> products)
//    {
//        JSONObject parameters = new JSONObject();
//
//        try
//        {
//            parameters.put("StoreNo", storeId);
//            parameters.put("CountryCode", DEFAULT_COUNTRY_CODE);
//            parameters.put("Language", DEFAULT_LANGUAGE);
//            parameters.put("OrderDate", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
//            parameters.put("IsCashPayment", isCashPayment);
//            parameters.put("Name", name);
//            parameters.put("PhoneNumber", phoneNumber);
//            parameters.put("VendorId", vendorId);
//            parameters.put("DeliverTo", deliverAddress);
//            parameters.put("Products", parameters);
//        }
//        catch (JSONException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    Order getOrderInfo(String vendorId, Order order)
//    {
//        Order orderInfo;
//        try
//        {
//            JSONObject parameters = new JSONObject();
//            parameters.put("CountryCode", DEFAULT_COUNTRY_CODE);
//            parameters.put("VendorId", vendorId);
//            parameters.put("OrderId", order.id);
//
//            JSONObject response = Internet.sendPost("/order/status", parameters);
//            orderInfo = new Order(
//                    response.getString("id"),
//                    response.getString("status"),
//                    response.getString("info")
//            );
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return order;
//    }
//}

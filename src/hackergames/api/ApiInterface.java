package hackergames.api;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class ApiInterface
{
    static Internet getInternet(String urlExtra, JSONObject parameters) throws MalformedURLException {
        URL url = new URL("https://hackathon.dominos.cloud" + urlExtra);

    }

    List<Pizza> getAll()
    {
        //
    }

    boolean pizzaExists(String pizza)
    {
        //
    }

    void placeOrder(String orderID, List<Product> pizzae) throws /*PizzaOrder*/Exception // TODO exception
    {
        //
    }

    Order getOrderInfo(String country, String vendorId, Order order)
    {
        getInternet("/order/status", order.toJSON());
    }
}

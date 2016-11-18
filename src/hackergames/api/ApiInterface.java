package hackergames.api;

import java.util.List;


public class ApiInterface
{
    static Internet getInternet(String url) {
        //
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

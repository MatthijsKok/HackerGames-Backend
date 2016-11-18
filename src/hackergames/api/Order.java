package hackergames.api;


public class Order
{
    String id, status, info;

    public Order(String id, String status, String info)
    {
        this.id = id;
        this.status = status;
        this.info = info;
    }
}

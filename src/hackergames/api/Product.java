package hackergames.api;

import java.util.List;


public class Product
{
    String productCode, sizeCode;
    int price;
    List<String> additions;

    public Product(String productCode, String sizeCode, int price, List<String> additions)
    {
        this.productCode = productCode;
        this.sizeCode = sizeCode;
        this.price = price;
        this.additions = additions;
    }
}

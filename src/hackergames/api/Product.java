package hackergames.api;

import org.json.JSONException;
import org.json.JSONObject;

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

    String toJsonString()
    {
        final JSONObject obj = new JSONObject();

        try
        {
            obj.put("ProductCode", productCode);
            obj.put("Price", price);
            obj.put("SizeCode", sizeCode);
            obj.put("Additions", additions);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return obj.toString();
    }
}

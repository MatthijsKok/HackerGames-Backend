package hackergames.api;

import org.json.JSONObject;

import java.util.List;


public class Pizza
{
    String productCode, name, description, template;
    int quantity, maxQuantity;
    List<String> sizeCode;

    public Pizza(String productCode, String name, String description, String template, int quantity, int maxQuantity,
                 List<String> sizeCode)
    {
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.template = template;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.sizeCode = sizeCode;
    }
}

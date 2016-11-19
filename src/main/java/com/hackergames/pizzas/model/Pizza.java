package com.hackergames.pizzas.model;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


/**
 * A pizza as configured by a user. For example, its size and price have been determined.
 * This class should be used when posting an order to the API.
 */
@Entity
public final class Pizza
{
    @Id
    private final String productCode;
    private final String price;
    private final String sizeCode;
    private final List<String> additions;


    /**
     * Creates a new {@code PizzaOptions} from a {@code JSONObject}.
     *
     * @param json the json
     * @return a new {@code PizzaOptions}
     */
    public static Pizza fromJson(final JSONObject json)
    {
        final JSONArray jAdditions = json.getJSONArray("Additions");
        final List<String> additions = new ArrayList<>();
        for (int i = 0; i < jAdditions.length(); i++)
        {
            additions.add(jAdditions.getString(i));
        }

        return new Pizza(
                json.getString("ProductCode"),
                json.getString("Price"),
                json.getString("SizeCode"),
                additions
        );
    }

    /**
     * Constructs a new {@code Pizza}.
     *
     * @param productCode the unique product code
     * @param price       the price of the pizza
     * @param sizeCode    the size code of the pizza
     * @param additions   additional details of the pizza
     */
    public Pizza(final String productCode, final String price, final String sizeCode, final List<String> additions)
    {
        this.productCode = productCode;
        this.price = price;
        this.sizeCode = sizeCode;
        this.additions = additions;
    }


    public String getProductCode()
    {
        return productCode;
    }

    public String getPrice()
    {
        return price;
    }

    public String getSizeCode()
    {
        return sizeCode;
    }

    public List<String> getAdditions()
    {
        return additions;
    }
}

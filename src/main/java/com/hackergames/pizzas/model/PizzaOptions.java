package com.hackergames.pizzas.model;

import org.json.JSONObject;


/**
 * A pizza "template", containing the possible options for the pizza.
 */
public final class PizzaOptions
{
    private final String name;
    private final String description;
    private final String image;
    private final String pickupPrice;
    private final String deliveryPrice;
    private final String status;
    private final String componentStatus;
    private final String itemType;
    private final String itemCode;
    private final boolean hnhEnabled;


    /**
     * Creates a new {@code PizzaOptions} from a {@code JSONObject}.
     *
     * @param json the json
     * @return a new {@code PizzaOptions}
     */
    public static PizzaOptions fromJson(final JSONObject json)
    {
        final JSONObject price = json.getJSONObject("Price");
        final JSONObject linkedItem = json.getJSONObject("LinkedItem");

        return new PizzaOptions(
                json.getString("Name"),
                json.getString("Description"),
                json.getString("Image"),
                price.getString("Pickup"),
                price.getString("Delivered"),
                json.getString("Status"),
                json.getString("ComponentStatus"),
                linkedItem.getString("ItemType"),
                linkedItem.getString("ItemCode"),
                json.getBoolean("HalfnHalfEnabled")
        );
    }

    /**
     * Constructs a new {@code PizzaOptions}.
     *
     * @param name            the pizza's name
     * @param description     the pizza's description
     * @param image           the relative path to an image of the pizza, no idea how to get the absolute path
     * @param pickupPrice     the price if the pizza is picked up at the store
     * @param deliveryPrice   the price if the pizza is delivered at home
     * @param status          whether the pizza is available
     * @param componentStatus whether the pizza's components are available
     * @param itemType        the type of item; usually "product"
     * @param itemCode        the unique code of the pizza
     * @param hnhEnabled      whether half'n'half is enabled for the pizza
     */
    public PizzaOptions(final String name, final String description, final String image, final String pickupPrice,
                        final String deliveryPrice, final String status, final String componentStatus,
                        final String itemType, String itemCode, boolean hnhEnabled)
    {

        this.name = name;
        this.description = description;
        this.image = image;
        this.pickupPrice = pickupPrice;
        this.deliveryPrice = deliveryPrice;
        this.status = status;
        this.componentStatus = componentStatus;
        this.itemType = itemType;
        this.itemCode = itemCode;
        this.hnhEnabled = hnhEnabled;
    }


    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getImage()
    {
        return image;
    }

    public String getPickupPrice()
    {
        return pickupPrice;
    }

    public String getDeliveryPrice()
    {
        return deliveryPrice;
    }

    public String getStatus()
    {
        return status;
    }

    public String getComponentStatus()
    {
        return componentStatus;
    }

    public String getItemType()
    {
        return itemType;
    }

    public String getItemCode()
    {
        return itemCode;
    }

    public boolean isHnhEnabled()
    {
        return hnhEnabled;
    }
}

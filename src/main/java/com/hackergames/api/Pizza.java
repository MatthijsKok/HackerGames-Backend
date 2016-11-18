package com.hackergames.api;

import lombok.Getter;
import org.json.JSONObject;


public class Pizza
{
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private String image;
    @Getter
    private String pickupPrice;
    @Getter
    private String deliveryPrice;
    @Getter
    private String status;
    @Getter
    private String componentStatus;
    @Getter
    private String itemType;
    @Getter
    private String itemCode;
    @Getter
    private String subMenu;
    @Getter
    boolean hnhAble;


    /**
     * Creates a new {@code Pizza} from a JSON string.
     *
     * @param jso     the JSON string
     * @param subMenu the type of pizza
     * @return a new {@code Pizza}
     */
    public static Pizza fromJson(final JSONObject jso, final String subMenu)
    {
        JSONObject price = jso.getJSONObject("Price");
        JSONObject linkedItem = jso.getJSONObject("LinkedItem");

        return new Pizza(
                jso.getString("Name"),
                jso.getString("Description"),
                jso.getString("Image"),
                price.getString("Pickup"),
                price.getString("Delivered"),
                jso.getString("Status"),
                jso.getString("ComponentStatus"),
                linkedItem.getString("ItemType"),
                linkedItem.getString("ItemCode"),
                subMenu,
                jso.getBoolean("HalfnHalfEnabled")
        );
    }

    public Pizza(String name, String description, String image, String pickupPrice, String deliveryPrice, String status,
                 String componentStatus, String itemType, String itemCode, String subMenu, boolean hnhAble)
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
        this.subMenu = subMenu;
        this.hnhAble = hnhAble;
    }
}

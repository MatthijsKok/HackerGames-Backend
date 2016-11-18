package com.hackergames.api;

import org.json.JSONObject;


public class Pizza
{
    String name, description, image, pickupPrice, deliveryPrice, status, componentStatus, itemType, itemCode, subMenu;
    boolean hnhAble;


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

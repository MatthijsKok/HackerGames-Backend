package com.hackergames.api;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


/**
 * Additional functions because org.json sucks kinda.
 */
public class JsonUtil
{
    public static List<String> jsonArrayToList(final JSONArray array)
    {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++)
        {
            list.add(array.getString(i));
        }

        return list;
    }
}

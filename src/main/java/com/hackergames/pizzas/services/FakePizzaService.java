package com.hackergames.pizzas.services;

import com.hackergames.pizzas.model.PizzaOptions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Fake pizza service simulating Domino's.
 */
@Service
public final class FakePizzaService implements PizzaService {
    @Override
    public List<PizzaOptions> getAllPizzas() {
        final List<PizzaOptions> pizzas = new ArrayList<>();

        try {
            final JSONObject menu = Requester.getRequest("https://hackathon-menu.dominos.cloud/", "/Rest/nl/menus/30544/en");
            final JSONArray jPizzas = menu.getJSONArray("MenuPages").getJSONObject(0).getJSONArray("SubMenus");
            for (int i = 0; i < jPizzas.length(); i++) {
                final JSONObject subMenu = jPizzas.getJSONObject(i);
                final JSONArray jProducts = subMenu.getJSONArray("Products");

                for (int j = 0; j < jProducts.length(); j++) {
                    pizzas.add(PizzaOptions.fromJson(jProducts.getJSONObject(j)));
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return pizzas;
    }
}

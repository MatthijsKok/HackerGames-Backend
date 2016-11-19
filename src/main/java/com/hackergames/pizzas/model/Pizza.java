package com.hackergames.pizzas.model;

import com.hackergames.pizzas.services.FakePizzaService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Pizza implements Serializable {
    private static final long serialVersionUID = 4792120082595242177L;

    @Id
    @Getter
    @Setter
    private String productCode;
    @Getter
    @Setter
    private String price;
    @Getter
    @Setter
    private String sizeCode;
    @Getter
    @Setter
    private ArrayList<String> additions;

    private static List<PizzaOptions> pos = (new FakePizzaService()).getAllPizzas();


    public static Pizza fromName(String name, String size, ArrayList<String> additions) {
        for (PizzaOptions po : pos) {
            if (po.getName().equals(name)) {
                return new Pizza(po.getItemCode(), po.getDeliveryPrice(), size, additions);
            }
        }
        return null;
    }


    public Pizza() {
    }

    public Pizza(String productCode, String price, String sizeCode, ArrayList<String> additions) {
        this.productCode = productCode;
        this.price = price;
        this.sizeCode = sizeCode;
        this.additions = additions;
    }
}

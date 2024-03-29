package com.hackergames.pizzas.model;

import com.hackergames.pizzas.services.FakePizzaService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
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
            if (po.getName().equalsIgnoreCase(name)) {
                return new Pizza(po.getItemCode(), po.getDeliveryPrice(), size, additions);
            }
        }
        return null;
    }

    public Pizza() {
    }


    /**
     * Returns more details on this {@code Pizza}.
     */
    public PizzaOptions getDetails() {
        for (PizzaOptions po : pos) {
            if (po.getItemCode().equals(this.productCode)) {
                return po;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (productCode != null ? !productCode.equals(pizza.productCode) : pizza.productCode != null) return false;
        if (price != null ? !price.equals(pizza.price) : pizza.price != null) return false;
        if (sizeCode != null ? !sizeCode.equals(pizza.sizeCode) : pizza.sizeCode != null) return false;
        return additions != null ? additions.equals(pizza.additions) : pizza.additions == null;
    }
}

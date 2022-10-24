package com.fusion.reactor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {

    private long id;

    private double price;

    private long userId;

    private String name;

    public Order(final double price, final String name) {
        this.price = price;
        this.name = name;
    }
}

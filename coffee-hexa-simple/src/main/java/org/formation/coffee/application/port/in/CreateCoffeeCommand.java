package org.formation.coffee.application.port.in;

import org.formation.coffee.domain.model.Price;

import java.util.Objects;

public record CreateCoffeeCommand(String name, Price price) {

    public CreateCoffeeCommand {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(price, "Price cannot be null");
    }
}

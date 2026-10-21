package org.formation.coffee.application.port.in;

import jakarta.annotation.Nullable;
import org.formation.coffee.domain.model.CoffeeId;
import org.formation.coffee.domain.model.Price;

import java.util.Objects;

public record UpdateCoffeeCommand(CoffeeId id, @Nullable String name, @Nullable Price price) {

    public UpdateCoffeeCommand {
        Objects.requireNonNull(id, "Id cannot be null");
    }
}

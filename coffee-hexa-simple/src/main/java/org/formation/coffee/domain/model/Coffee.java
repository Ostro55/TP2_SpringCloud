package org.formation.coffee.domain.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import org.formation.coffee.domain.exception.DomainValidationException;

import java.util.List;
import java.util.Objects;

public final class Coffee {

    public static final int MAX_NAME_LENGTH = 255;

    private final @Nullable CoffeeId id;
    private String name;
    private Price price;
    private final List<Review> reviews;

    private Coffee(@Nullable CoffeeId id, String name, Price price, List<Review> reviews) {
        this.id = id;
        this.name = requireValidName(name);
        this.price = Objects.requireNonNull(price, "price must not be null");
        this.reviews = List.copyOf(reviews);
    }
    
    public static Coffee create(String name, Price price) {
        return new Coffee(null, name, price, List.of());
    }
    
    public static Coffee reconstitute(CoffeeId id, String name, Price price, List<Review> reviews) {
        return new Coffee(Objects.requireNonNull(id, "id must not be null"), name, price, reviews);
    }

    public void rename(String newName) {
        this.name = requireValidName(newName);
    }

    public void changePrice(Price newPrice) {
        this.price = Objects.requireNonNull(newPrice, "price must not be null");
    }

    public boolean isNew() {
        return id == null;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public @Nullable CoffeeId getId() {
        return id;
    }

    private static String requireValidName(@Nullable String name) {
        if (name == null || name.isBlank()) {
            throw new DomainValidationException("Coffee must not be null or blank");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new DomainValidationException("Coffee name must not exceed " + MAX_NAME_LENGTH + " characters");
        }
        return name;
    }

    @Override
    public String toString() {
        return "Coffee[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ']';
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return other instanceof Coffee coffee && id != null && id.equals(coffee.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : System.identityHashCode(this);
    }
}

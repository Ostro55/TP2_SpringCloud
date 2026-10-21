package org.formation.coffee.domain.model;

import org.formation.coffee.domain.exception.DomainValidationException;

import java.util.Objects;

public record Review(ReviewId id, String author, String comment, int rating) {

    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;

    public Review {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(comment, "comment must not be null");
        if (author.isBlank()) {
            throw new DomainValidationException("Author must not be null or blank");
        }
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new DomainValidationException("Rating must be between " + MIN_RATING + " and " + MAX_RATING + ", got " + rating);
        }
    }
}

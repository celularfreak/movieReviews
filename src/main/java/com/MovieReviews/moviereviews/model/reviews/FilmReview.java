package com.MovieReviews.moviereviews.model.reviews;


import com.MovieReviews.moviereviews.model.Review;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FilmReview extends Review {

    @NotNull(message = "El id de la película no puede ser nulo")
    @Min(value = 1, message = "El id de la película debe ser un número positivo")
    private int filmId;

    private FilmReview(String username, int rating, String comment, LocalDate reviewDate, int filmId) {
        super(username, rating, comment, reviewDate);
        this.filmId = filmId;
    }
}

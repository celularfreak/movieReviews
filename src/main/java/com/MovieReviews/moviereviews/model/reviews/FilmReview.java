package com.MovieReviews.moviereviews.model.reviews;

import com.MovieReviews.moviereviews.model.Review;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FilmReview extends Review {


    @Pattern(regexp = "^[1-9][0-9]*$", message = "El id de la película debe ser un número positivo")
    private int filmId;

    private FilmReview(String username, int rating, String comment, LocalDate reviewDate, int filmId) {
        super(username, rating, comment, reviewDate);
        this.filmId = filmId;
    }
}

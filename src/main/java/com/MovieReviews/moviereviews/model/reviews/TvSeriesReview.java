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
public class TvSeriesReview extends Review {

    @NotNull
    @Pattern(regexp = "^[1-9][0-9]*$", message = "El id de la serie de tv debe ser un número positivo")
    private int tvSeriesId;

    private TvSeriesReview(String username, int rating, String comment, LocalDate reviewDate, int tvSeriesId) {
        super(username, rating, comment, reviewDate);
        this.tvSeriesId = tvSeriesId;
    }
}

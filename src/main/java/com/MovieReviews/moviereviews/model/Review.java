package com.MovieReviews.moviereviews.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String username;

    private Long filmId;

    private Long tvSeriesId;

    private Long mediaId;

    @Min(value = 0)
    @Max(value = 10)
    private int rating;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 1000)
    private String comment;

    private LocalDate reviewDate;

    private Review(Long id, String username, int rating, String comment, LocalDate reviewDate, Long mediaId, boolean isFilm) {
        this.id = id;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        if (isFilm) {
            this.filmId = mediaId;
        } else {
            this.tvSeriesId = mediaId;
        }
    }

    public static Review createFilmReview(Long id, String username, int rating, String comment, LocalDate reviewDate, Long filmId) {
        return new Review(id, username, rating, comment, reviewDate, filmId, true);
    }

    public static Review createTvSeriesReview(Long id, String username, int rating, String comment, LocalDate reviewDate, Long tvSeriesId) {
        return new Review(id, username, rating, comment, reviewDate, tvSeriesId, false);
    }

}
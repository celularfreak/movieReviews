package com.MovieReviews.moviereviews.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    @ManyToOne
    @JoinColumn(name = "tv_series_id")
    private TvSeries tvSeries;
    @NonNull
    private LocalDate reviewDate;

    public Review(@NonNull User user, @NonNull LocalDate reviewDate) {
        this.user = user;
        this.reviewDate = reviewDate;
    }
}
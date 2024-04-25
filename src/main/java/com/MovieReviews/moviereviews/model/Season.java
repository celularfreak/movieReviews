package com.MovieReviews.moviereviews.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull
    private int seasonNumber;
    @ManyToOne
    @JoinColumn(name = "tv_series_id")
    @NonNull
    private TvSeries tvSeries;

    public Season(@NonNull int seasonNumber, @NonNull TvSeries tvSeries) {
        this.seasonNumber = seasonNumber;
        this.tvSeries = tvSeries;
    }
}

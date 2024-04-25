package com.MovieReviews.moviereviews.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TvSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull
    private int numberSeasons;
    @NonNull
    private int numberEpisodes;
    @NonNull
    @Column(name = "finish_date")
    private LocalDate finishDate;

    public TvSeries(@NonNull int numberSeasons, @NonNull int numberEpisodes, @NonNull LocalDate finishDate) {
        this.numberSeasons = numberSeasons;
        this.numberEpisodes = numberEpisodes;
        this.finishDate = finishDate;
    }
}
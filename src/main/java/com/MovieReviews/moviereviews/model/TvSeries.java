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
public class TvSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @Column(name = "launch_date")
    private LocalDate launchDate;

    @NonNull
    private String genre;

    @NonNull
    private int numberSeasons;

    @NonNull
    private int numberEpisodes;

    @NonNull
    @Column(name = "finish_date")
    private LocalDate finishDate;

    public TvSeries(@NonNull String title, @NonNull LocalDate launchDate, @NonNull String genre, @NonNull int numberSeasons, @NonNull int numberEpisodes, @NonNull LocalDate finishDate) {
        this.title = title;
        this.launchDate = launchDate;
        this.genre = genre;
        this.numberSeasons = numberSeasons;
        this.numberEpisodes = numberEpisodes;
        this.finishDate = finishDate;
    }
}

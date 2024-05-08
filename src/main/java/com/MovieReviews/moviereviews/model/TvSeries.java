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
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Column(nullable = false, name = "launch_date", columnDefinition = "DATE")
    private LocalDate launchDate;

    @Column(nullable = false)
    private String genre;
    
    @Column(nullable = false, name = "number_seasons")
    private int numberSeasons;

    
    @Column(nullable = false, name = "number_episodes")
    private int numberEpisodes;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    public TvSeries( String title,  LocalDate launchDate,  String genre,
                     int numberSeasons,  int numberEpisodes, LocalDate finishDate) {
        this.title = title;
        this.launchDate = launchDate;
        this.genre = genre;
        this.numberSeasons = numberSeasons;
        this.numberEpisodes = numberEpisodes;
        this.finishDate = finishDate;
    }
}

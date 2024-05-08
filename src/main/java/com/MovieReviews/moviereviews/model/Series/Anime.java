package com.MovieReviews.moviereviews.model.Series;

import com.MovieReviews.moviereviews.model.TvSeries;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime extends TvSeries {
    @Column(nullable = false, length = 50)
    private String animationStudio;

    public Anime(long id, String title,  LocalDate launchDate,  String genre,
                 int numberSeasons, int numberEpisodes,  LocalDate finishDate,  String animationStudio) {
        super(id, title, launchDate, genre, numberSeasons, numberEpisodes, finishDate);
        this.animationStudio = animationStudio;
    }
}

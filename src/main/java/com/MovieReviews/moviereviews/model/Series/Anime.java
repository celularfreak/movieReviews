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
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime extends TvSeries {
    private String animationStudio;

    public Anime(@NonNull String title, @NonNull LocalDate launchDate, @NonNull String genre, @NonNull int numberSeasons, @NonNull int numberEpisodes, @NonNull LocalDate finishDate, String animationStudio) {
        super(title, launchDate, genre, numberSeasons, numberEpisodes, finishDate);
        this.animationStudio = animationStudio;
    }
}

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
    @NonNull
    private String animationStudio;

    public Anime(long id, @NonNull String title, @NonNull LocalDate launchDate, @NonNull String genre, int numberSeasons, int numberEpisodes, @NonNull LocalDate finishDate, String animationStudio) {
        super(id, title, launchDate, genre, numberSeasons, numberEpisodes, finishDate);
        this.animationStudio = animationStudio;
    }

}

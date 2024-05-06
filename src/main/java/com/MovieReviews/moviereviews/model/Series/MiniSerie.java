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
public class MiniSerie extends TvSeries {

    public MiniSerie(@NonNull String title, @NonNull LocalDate launchDate, @NonNull String genre, @NonNull int numberEpisodes, @NonNull LocalDate finishDate) {
        super(title, launchDate, genre, 1, numberEpisodes, finishDate);
    }
}

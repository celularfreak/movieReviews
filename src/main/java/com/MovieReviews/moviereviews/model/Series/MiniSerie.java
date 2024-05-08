package com.MovieReviews.moviereviews.model.Series;

import com.MovieReviews.moviereviews.model.TvSeries;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MiniSerie extends TvSeries {

    public MiniSerie(long id, String title, LocalDate launchDate, String genre, int numberEpisodes, @NonNull LocalDate finishDate) {
        super(id, title, launchDate, genre, 1, numberEpisodes, finishDate);
    }

}

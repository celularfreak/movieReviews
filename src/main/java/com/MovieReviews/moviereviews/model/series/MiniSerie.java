package com.MovieReviews.moviereviews.model.series;

import com.MovieReviews.moviereviews.model.TvSeries;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MiniSerie extends TvSeries {

    public MiniSerie(String title, LocalDate launchDate, String genre, int numberEpisodes, @NotNull LocalDate finishDate) {
        super(title, launchDate, genre, 1, numberEpisodes, finishDate);
    }

}

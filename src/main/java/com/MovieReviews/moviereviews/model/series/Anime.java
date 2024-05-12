package com.MovieReviews.moviereviews.model.series;

import com.MovieReviews.moviereviews.model.TvSeries;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class Anime extends TvSeries {

    @NotNull(message = "El estudio de animación no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,]{1,100}$", message = "El estudio de animación debe tener entre 1 y 100 letras y admitir, espacios, puntos y comas")
    private String animationStudio;

    public Anime(String title,  LocalDate launchDate,  String genre,
                 int numberSeasons, int numberEpisodes,  LocalDate finishDate,  String animationStudio) {
        super(title, launchDate, genre, numberSeasons, numberEpisodes, finishDate);
        this.animationStudio = animationStudio;
    }
}

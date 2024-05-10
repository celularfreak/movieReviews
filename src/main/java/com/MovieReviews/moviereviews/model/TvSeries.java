package com.MovieReviews.moviereviews.model;

import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private int id;

    @NotNull(message = "El título no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,]{1,100}$", message = "El título debe tener entre 1 y 100 letras y admitir, espacios, puntos y comas")
    private String title;

    @NotNull(message = "La fecha de lanzamiento no puede ser nula")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate launchDate;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+(,[a-zA-Z]+)*$", message = "El género debe ser una palabra o varias separadas por comas")
    private String genre;
    
   @Min(value = 1, message = "El número de temporadas debe ser mayor a 0")
    private int numberSeasons;


    @Min(value = 1, message = "El número de episodios debe ser mayor a 0")
    private int numberEpisodes;

    @NotNull(message = "La fecha de finalizacion no puede ser nula")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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

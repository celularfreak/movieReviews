package com.MovieReviews.moviereviews.model;

import com.MovieReviews.moviereviews.model.reviews.TvSeriesReview;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;

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
    @PastOrPresent(message = "La fecha de lanzamiento debe ser en el pasado o en el presente")
    private LocalDate launchDate;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s,]{1,100}$", message = "El género debe tener entre 1 y 100 letras y admitir espacios y comas")
    private String genre;
    
   @Min(value = 1, message = "El número de temporadas debe ser mayor a 0")
    private int numberSeasons;


    @Min(value = 1, message = "El número de episodios debe ser mayor a 0")
    private int numberEpisodes;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate finishDate;

    @OneToMany(mappedBy = "tvSeriesId", cascade = CascadeType.ALL)
    private List<TvSeriesReview> reviews;

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

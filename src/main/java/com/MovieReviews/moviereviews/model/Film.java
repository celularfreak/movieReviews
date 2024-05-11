package com.MovieReviews.moviereviews.model;

import com.MovieReviews.moviereviews.model.reviews.FilmReview;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.util.List;
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
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NotNull(message = "El título no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,]{1,100}$", message = "El título debe tener entre 1 y 100 letras y admitir, espacios, puntos y comas")
    private String title;

    @Pattern(regexp =  "^[a-zA-Z0-9\\s.,]{1,50}$", message = "El director debe tener entre 1 y 50 letras, y admitir, espacios, puntos y comas")
    @NotNull(message = "El director no puede ser nulo")
    private String director;

    @NotNull(message = "La fecha de lanzamiento no puede ser nula")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent(message = "La fecha de lanzamiento debe ser en el pasado o en el presente")
    private LocalDate launchDate;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s,]{1,100}$", message = "El género debe tener entre 1 y 100 letras y admitir espacios y comas")
    private String genre;

    @OneToMany(mappedBy = "filmId", cascade = CascadeType.ALL)
    private List<FilmReview> reviews;

    public Film(@NonNull String title, @NonNull String director, LocalDate launchDate, @NonNull String genre) {
        this.title = title;
        this.director = director;
        this.launchDate = launchDate;
        this.genre = genre;
    }
}

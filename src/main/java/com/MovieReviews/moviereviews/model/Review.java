package com.MovieReviews.moviereviews.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String username;

    @Min(value = 0)
    @Max(value = 10)
    private int rating;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 1000)
    private String comment;

    @NotNull(message = "La fecha de la review no puede ser nula")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent(message = "La fecha de la review debe ser en el pasado o en el presente")
    private LocalDate reviewDate;


    public Review(String username, int rating, String comment, LocalDate reviewDate) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
}
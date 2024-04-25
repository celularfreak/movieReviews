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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String director;
    @NonNull
    @Column(name = "launch_date")
    private LocalDate launchDate;
    @NonNull
    private String genre;

    public Film(@NonNull String title, @NonNull String director, @NonNull LocalDate launchDate, @NonNull String genre) {
        this.title = title;
        this.director = director;
        this.launchDate = launchDate;
        this.genre = genre;
    }
}

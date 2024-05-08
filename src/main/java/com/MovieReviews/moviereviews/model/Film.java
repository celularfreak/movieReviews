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
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String director;
    
    @Column(nullable = false, name = "launch_date")
    private LocalDate launchDate;
    
    @Column(nullable = false)
    private String genre;

    public Film( String title,  String director,  LocalDate launchDate,  String genre) {
        this.title = title;
        this.director = director;
        this.launchDate = launchDate;
        this.genre = genre;
    }
}

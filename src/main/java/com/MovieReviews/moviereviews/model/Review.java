package com.MovieReviews.moviereviews.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


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
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Min(value = 0)
    @Max(value = 10)
    private int rating;

    @Size(min = 4, max = 1000)
    @Column(nullable = false, length = 1000)
    private String comment;

    @Column(nullable = false)
    private LocalDate reviewDate;

    public Review(Long id, Long userId, int rating, String comment, LocalDate reviewDate) {
        this.id = id;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
}

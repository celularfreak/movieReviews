package com.MovieReviews.moviereviews.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long entityId; // Puede ser la id del film o de la tvserie, dependiendo de la revisión

    @Column(nullable = false)
    private int rating;

    @Column(length = 1000) // Se puede ajustar el tamaño según sea necesario
    private String comment;

    @Column(nullable = false)
    private LocalDate reviewDate;

    // Constructor
    public Review(Long userId, Long entityId, int rating, String comment, LocalDate reviewDate) {
        this.userId = userId;
        this.entityId = entityId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
}


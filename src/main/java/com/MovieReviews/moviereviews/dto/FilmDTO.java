package com.MovieReviews.moviereviews.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    private Long id;
    private String title;
    private String director;
    private LocalDate launchDate;
    private String genre;
}

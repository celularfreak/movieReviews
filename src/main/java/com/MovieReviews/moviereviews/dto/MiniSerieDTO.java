package com.MovieReviews.moviereviews.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MiniSerieDTO {
    private Long id;
    private String title;
    private LocalDate launchDate;
    private String genre;
    private int numberEpisodes;
    private LocalDate finishDate;
}

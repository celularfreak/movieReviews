package com.MovieReviews.moviereviews.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;



@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty(message = "You must supply an email")
    private String email;

    @NotEmpty(message = "You must supply a name")
    private String name;

    @NotEmpty(message = "You must supply a password")
    private String password;

    private int karma;



}

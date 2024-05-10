package com.MovieReviews.moviereviews.controller.reviews;

import com.MovieReviews.moviereviews.model.reviews.FilmReview;
import com.MovieReviews.moviereviews.service.reviews.FilmReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film-reviews")
public class FilmReviewController {

    private final FilmReviewService filmReviewService;

    @Autowired
    public FilmReviewController(FilmReviewService reviewService, FilmReviewService filmReviewService) {
        this.filmReviewService = filmReviewService;
    }

    @GetMapping
    public ResponseEntity<List<FilmReview>> getAllFilmReviews() {
        List<FilmReview> filmReviews = filmReviewService.getAllFilmReviews();
        return new ResponseEntity<>(filmReviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmReview> getFilmReviewById(@PathVariable int id) {
        FilmReview filmReview = filmReviewService.getFilmReviewById(id);
        if (filmReview != null) {
            return new ResponseEntity<>(filmReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addFilmReview")
    public ResponseEntity<?> addFilmReview(@Valid @RequestBody FilmReview filmReview) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            filmReview.setUsername(username);
            FilmReview addedFilmReview = filmReviewService.addFilmReview(filmReview);

            return new ResponseEntity<>(addedFilmReview, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilmReview(@PathVariable int id, @Valid @RequestBody FilmReview filmReview) {
        try {
            FilmReview updatedFilmReview = filmReviewService.updateFilmReview(id, filmReview);
            if (updatedFilmReview != null) {
                return new ResponseEntity<>(updatedFilmReview, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmReview(@PathVariable int id) {
        filmReviewService.deleteFilmReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

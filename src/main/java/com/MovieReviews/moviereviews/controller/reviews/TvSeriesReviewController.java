package com.MovieReviews.moviereviews.controller.reviews;

import com.MovieReviews.moviereviews.model.reviews.TvSeriesReview;
import com.MovieReviews.moviereviews.service.reviews.TvSeriesReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tv-series-reviews")
public class TvSeriesReviewController {

    private final TvSeriesReviewService tvSeriesReviewService;

    @Autowired
    public TvSeriesReviewController(TvSeriesReviewService tvSeriesReviewService) {
        this.tvSeriesReviewService = tvSeriesReviewService;
    }

    @GetMapping
    public ResponseEntity<List<TvSeriesReview>> getAllTvSeriesReviews() {
        List<TvSeriesReview> tvSeriesReviews = tvSeriesReviewService.getAllTvSeriesReviews();
        return new ResponseEntity<>(tvSeriesReviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvSeriesReview> getTvSeriesReviewById(@PathVariable int id) {
        TvSeriesReview tvSeriesReview = tvSeriesReviewService.getTvSeriesReviewById(id);
        if (tvSeriesReview != null) {
            return new ResponseEntity<>(tvSeriesReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTvSeriesReview(@Valid @RequestBody TvSeriesReview tvSeriesReview) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            tvSeriesReview.setUsername(username);
            TvSeriesReview addedTvSeriesReview = tvSeriesReviewService.addTvSeriesReview(tvSeriesReview);

            return new ResponseEntity<>(addedTvSeriesReview, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTvSeriesReview(@PathVariable int id, @Valid @RequestBody TvSeriesReview tvSeriesReview) {
        try {
            TvSeriesReview updatedTvSeriesReview = tvSeriesReviewService.updateTvSeriesReview(id, tvSeriesReview);
            if (updatedTvSeriesReview != null) {
                return new ResponseEntity<>(updatedTvSeriesReview, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTvSeriesReview(@PathVariable int id) {
        tvSeriesReviewService.deleteTvSeriesReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

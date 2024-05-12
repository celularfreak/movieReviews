package com.MovieReviews.moviereviews.service;

import com.MovieReviews.moviereviews.model.Review;
import com.MovieReviews.moviereviews.repositories.FilmRepository;
import com.MovieReviews.moviereviews.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, FilmRepository filmRepository) {
        this.reviewRepository = reviewRepository;
        this.filmRepository = filmRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review updateReview(int id, Review review) {
        validateReview(review);
        Review existingReview = reviewRepository.findById(id).orElse(null);
        if (existingReview != null) {
            existingReview.setRating(review.getRating());
            existingReview.setComment(review.getComment());
            existingReview.setReviewDate(review.getReviewDate());
            return reviewRepository.save(existingReview);
        }
        return null;
    }

    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }

    private void validateReview(Review review) {
    }

    public Review getReviewByUsername(String username) {
        return reviewRepository.findByUsername(username);
    }

}


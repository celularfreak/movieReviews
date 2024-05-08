package com.MovieReviews.moviereviews.service;

import com.MovieReviews.moviereviews.model.Review;
import com.MovieReviews.moviereviews.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review addReview(Review review) {
        validateReview(review);
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review review) {
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

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private void validateReview(Review review) {
        if (review.getRating() < 0 || review.getRating() > 10) {
            throw new IllegalArgumentException("La puntuacion tiene que ser un numero entre 0 y 10.");
        }
        if (review.getComment().length() > 1000) {
            throw new IllegalArgumentException("El comentario no puede tener mas de 1000 caractares.");
        }
    }
}


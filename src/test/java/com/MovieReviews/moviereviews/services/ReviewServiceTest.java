package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.Review;
import com.MovieReviews.moviereviews.repositories.ReviewRepository;
import com.MovieReviews.moviereviews.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllReviews() {
        // Arrange
        List<Review> reviews = new ArrayList<>();
        // Populate reviews with test data
        when(reviewRepository.findAll()).thenReturn(reviews);

        // Act
        List<Review> result = reviewService.getAllReviews();

        // Assert
        assertEquals(reviews, result);
    }

    @Test
    void getReviewById_ExistingId_ReturnsReview() {
        // Arrange
        int id = 1;
        Review review = new Review();
        // Populate review with test data
        when(reviewRepository.findById(id)).thenReturn(Optional.of(review));

        // Act
        Review result = reviewService.getReviewById(id);

        // Assert
        assertEquals(review, result);
    }

    @Test
    void getReviewById_NonExistingId_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        when(reviewRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Review result = reviewService.getReviewById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void updateReview_ExistingReview_ReturnsUpdatedReview() {
        // Arrange
        int id = 1;
        Review existingReview = new Review();
        // Populate existingReview with test data
        Review updatedReview = new Review();
        // Populate updatedReview with test data
        when(reviewRepository.findById(id)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(existingReview)).thenReturn(updatedReview);

        // Act
        Review result = reviewService.updateReview(id, existingReview);

        // Assert
        assertEquals(updatedReview, result);
    }

    @Test
    void updateReview_NonExistingReview_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        Review nonExistingReview = new Review();
        // Populate nonExistingReview with test data
        when(reviewRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Review result = reviewService.updateReview(id, nonExistingReview);

        // Assert
        assertNull(result);
    }

    @Test
    void deleteReview_ValidId_DeletesReview() {
        // Arrange
        int id = 1;

        // Act
        reviewService.deleteReview(id);

        // Assert
        verify(reviewRepository, times(1)).deleteById(id);
    }
}

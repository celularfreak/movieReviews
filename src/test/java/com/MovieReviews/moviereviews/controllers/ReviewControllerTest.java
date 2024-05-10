package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.ReviewController;
import com.MovieReviews.moviereviews.model.Review;
import com.MovieReviews.moviereviews.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllReviews() {
        // Arrange
        List<Review> reviews = new ArrayList<>();
        // Populate reviews with test data

        when(reviewService.getAllReviews()).thenReturn(reviews);

        // Act
        ResponseEntity<List<Review>> responseEntity = reviewController.getAllReviews();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviews, responseEntity.getBody());
    }

    @Test
    void getReviewById_ExistingId() {
        // Arrange
        int id = 1;
        Review review = new Review();
        // Populate review with test data

        when(reviewService.getReviewById(id)).thenReturn(review);

        // Act
        ResponseEntity<Review> responseEntity = reviewController.getReviewById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(review, responseEntity.getBody());
    }

    @Test
    void getReviewById_NonExistingId() {
        // Arrange
        int id = 999; // Non-existing ID

        when(reviewService.getReviewById(id)).thenReturn(null);

        // Act
        ResponseEntity<Review> responseEntity = reviewController.getReviewById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateReview_ValidIdAndReview() {
        // Arrange
        int id = 1;
        Review review = new Review();
        // Populate review with test data

        when(reviewService.updateReview(id, review)).thenReturn(review);

        // Act
        ResponseEntity<?> responseEntity = reviewController.updateReview(id, review);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(review, responseEntity.getBody());
    }

    @Test
    void updateReview_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID
        Review review = new Review();
        // Populate review with test data

        when(reviewService.updateReview(id, review)).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = reviewController.updateReview(id, review);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateReview_InvalidReview() {
        // Arrange
        int id = 1;
        Review review = new Review();
        // Populate review with invalid test data

        doThrow(IllegalArgumentException.class).when(reviewService).updateReview(id, review);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reviewController.updateReview(id, review));
    }

    @Test
    void deleteReview_ValidId() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<Void> responseEntity = reviewController.deleteReview(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(reviewService, times(1)).deleteReview(id);
    }

    @Test
    void deleteReview_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID

        // Act
        ResponseEntity<Void> responseEntity = reviewController.deleteReview(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(reviewService, never()).deleteReview(id);
    }
}

package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.ReviewController;
import com.MovieReviews.moviereviews.model.Review;
import com.MovieReviews.moviereviews.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1L, 1L, 5, "Great movie", LocalDate.parse("2024-01-01")));
        reviews.add(new Review(2L, 2L, 4, "Enjoyed it", LocalDate.parse("2024-02-02")));
        when(reviewService.getAllReviews()).thenReturn(reviews);

        ResponseEntity<List<Review>> responseEntity = reviewController.getAllReviews();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetReviewById() {
        Review review = new Review(1L, 1L, 5, "Great movie", LocalDate.parse("2024-01-01"));
        when(reviewService.getReviewById(1L)).thenReturn(review);

        ResponseEntity<Review> responseEntity = reviewController.getReviewById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Great movie", responseEntity.getBody().getComment());
        assertEquals(5, responseEntity.getBody().getRating());
    }

    @Test
    public void testAddReview() {
        Review review = new Review(1L, 1L, 5, "Great movie", LocalDate.parse("2024-01-01"));
        when(reviewService.addReview(any(Review.class))).thenReturn(review);

        ResponseEntity<Review> responseEntity = reviewController.addReview(review);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Great movie", responseEntity.getBody().getComment());
        assertEquals(5, responseEntity.getBody().getRating());
    }

    @Test
    public void testUpdateReview() {
        Review review = new Review(1L, 1L, 5, "Great movie", LocalDate.parse("2024-01-01"));
        when(reviewService.updateReview(anyLong(), any(Review.class))).thenReturn(review);

        ResponseEntity<Review> responseEntity = reviewController.updateReview(1L, review);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Great movie", responseEntity.getBody().getComment());
        assertEquals(5, responseEntity.getBody().getRating());
    }

    @Test
    public void testDeleteReview() {
        doNothing().when(reviewService).deleteReview(1L);

        ResponseEntity<Void> responseEntity = reviewController.deleteReview(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(reviewService, times(1)).deleteReview(1L);
    }
}

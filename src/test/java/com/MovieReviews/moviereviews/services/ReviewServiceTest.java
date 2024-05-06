package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.Review;
import com.MovieReviews.moviereviews.repositories.ReviewRepository;
import com.MovieReviews.moviereviews.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1L, 1L, 5, "Great movie", LocalDate.parse("2024-01-01")));
        reviews.add(new Review(2L, 2L, 4, "Enjoyed it", LocalDate.parse("2024-02-02")));
        when(reviewRepository.findAll()).thenReturn(reviews);

        List<Review> result = reviewService.getAllReviews();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetReviewById() {
        Review review = new Review(1L, 1L, 5, "Great movie", LocalDate.parse("2024-01-01"));
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        Review result = reviewService.getReviewById(1L);

        assertEquals("Great movie", result.getComment());
        assertEquals(5, result.getRating());
    }

    @Test
    public void testAddReview() {
        Review review = new Review(1L, 1L, 5, "Great movie", LocalDate.parse("2024-01-01"));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review result = reviewService.addReview(review);

        assertEquals("Great movie", result.getComment());
        assertEquals(5, result.getRating());
    }

    @Test
    public void testUpdateReview() {
        Review review = new Review(1L, 1L, 5, "Great movie", LocalDate.parse("2024-01-01"));
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review updatedReview = new Review(1L, 1L, 5, "Amazing movie", LocalDate.parse("2024-01-01"));
        Review result = reviewService.updateReview(1L, updatedReview);

        assertEquals("Amazing movie", result.getComment());
        assertEquals(5, result.getRating());
    }

    @Test
    public void testDeleteReview() {
        doNothing().when(reviewRepository).deleteById(1L);

        reviewService.deleteReview(1L);

        verify(reviewRepository, times(1)).deleteById(1L);
    }
}

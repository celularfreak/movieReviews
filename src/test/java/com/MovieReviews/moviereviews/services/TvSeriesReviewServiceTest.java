package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.reviews.TvSeriesReview;
import com.MovieReviews.moviereviews.repositories.reviews.TvSeriesReviewRepository;
import com.MovieReviews.moviereviews.service.reviews.TvSeriesReviewService;
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

class TvSeriesReviewServiceTest {

    @Mock
    private TvSeriesReviewRepository tvSeriesReviewRepository;

    @InjectMocks
    private TvSeriesReviewService tvSeriesReviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTvSeriesReviews() {
        // Arrange
        List<TvSeriesReview> tvSeriesReviews = new ArrayList<>();
        // Populate tvSeriesReviews with test data
        when(tvSeriesReviewRepository.findAll()).thenReturn(tvSeriesReviews);

        // Act
        List<TvSeriesReview> result = tvSeriesReviewService.getAllTvSeriesReviews();

        // Assert
        assertEquals(tvSeriesReviews, result);
    }

    @Test
    void getTvSeriesReviewById_ExistingId_ReturnsTvSeriesReview() {
        // Arrange
        int id = 1;
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        // Populate tvSeriesReview with test data
        when(tvSeriesReviewRepository.findById(id)).thenReturn(Optional.of(tvSeriesReview));

        // Act
        TvSeriesReview result = tvSeriesReviewService.getTvSeriesReviewById(id);

        // Assert
        assertEquals(tvSeriesReview, result);
    }

    @Test
    void getTvSeriesReviewById_NonExistingId_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        when(tvSeriesReviewRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        TvSeriesReview result = tvSeriesReviewService.getTvSeriesReviewById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void addTvSeriesReview_ValidTvSeriesReview_ReturnsAddedTvSeriesReview() {
        // Arrange
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        // Populate tvSeriesReview with test data
        when(tvSeriesReviewRepository.save(tvSeriesReview)).thenReturn(tvSeriesReview);

        // Act
        TvSeriesReview result = tvSeriesReviewService.addTvSeriesReview(tvSeriesReview);

        // Assert
        assertEquals(tvSeriesReview, result);
    }

    @Test
    void updateTvSeriesReview_ExistingTvSeriesReview_ReturnsUpdatedTvSeriesReview() {
        // Arrange
        int id = 1;
        TvSeriesReview existingTvSeriesReview = new TvSeriesReview();
        // Populate existingTvSeriesReview with test data
        TvSeriesReview updatedTvSeriesReview = new TvSeriesReview();
        // Populate updatedTvSeriesReview with test data
        when(tvSeriesReviewRepository.findById(id)).thenReturn(Optional.of(existingTvSeriesReview));
        when(tvSeriesReviewRepository.save(existingTvSeriesReview)).thenReturn(updatedTvSeriesReview);

        // Act
        TvSeriesReview result = tvSeriesReviewService.updateTvSeriesReview(id, existingTvSeriesReview);

        // Assert
        assertEquals(updatedTvSeriesReview, result);
    }

    @Test
    void updateTvSeriesReview_NonExistingTvSeriesReview_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        TvSeriesReview nonExistingTvSeriesReview = new TvSeriesReview();
        // Populate nonExistingTvSeriesReview with test data
        when(tvSeriesReviewRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        TvSeriesReview result = tvSeriesReviewService.updateTvSeriesReview(id, nonExistingTvSeriesReview);

        // Assert
        assertNull(result);
    }

    @Test
    void deleteTvSeriesReview_ValidId_DeletesTvSeriesReview() {
        // Arrange
        int id = 1;

        // Act
        tvSeriesReviewService.deleteTvSeriesReview(id);

        // Assert
        verify(tvSeriesReviewRepository, times(1)).deleteById(id);
    }
}

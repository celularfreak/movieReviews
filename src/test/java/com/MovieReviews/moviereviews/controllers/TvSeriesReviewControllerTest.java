package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.reviews.TvSeriesReviewController;
import com.MovieReviews.moviereviews.model.reviews.TvSeriesReview;
import com.MovieReviews.moviereviews.service.reviews.TvSeriesReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TvSeriesReviewControllerTest {

    @Mock
    private TvSeriesReviewService tvSeriesReviewService;

    @InjectMocks
    private TvSeriesReviewController tvSeriesReviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTvSeriesReviews() {
        // Arrange
        List<TvSeriesReview> tvSeriesReviews = new ArrayList<>();
        // Populate tvSeriesReviews with test data

        when(tvSeriesReviewService.getAllTvSeriesReviews()).thenReturn(tvSeriesReviews);

        // Act
        ResponseEntity<List<TvSeriesReview>> responseEntity = tvSeriesReviewController.getAllTvSeriesReviews();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tvSeriesReviews, responseEntity.getBody());
    }

    @Test
    void getTvSeriesReviewById_ExistingId() {
        // Arrange
        int id = 1;
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        // Populate tvSeriesReview with test data

        when(tvSeriesReviewService.getTvSeriesReviewById(id)).thenReturn(tvSeriesReview);

        // Act
        ResponseEntity<TvSeriesReview> responseEntity = tvSeriesReviewController.getTvSeriesReviewById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tvSeriesReview, responseEntity.getBody());
    }

    @Test
    void getTvSeriesReviewById_NonExistingId() {
        // Arrange
        int id = 999; // Non-existing ID

        when(tvSeriesReviewService.getTvSeriesReviewById(id)).thenReturn(null);

        // Act
        ResponseEntity<TvSeriesReview> responseEntity = tvSeriesReviewController.getTvSeriesReviewById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void addTvSeriesReview_ValidTvSeriesReview() {
        // Arrange
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        // Populate tvSeriesReview with test data

        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);

        when(tvSeriesReviewService.addTvSeriesReview(tvSeriesReview)).thenReturn(tvSeriesReview);

        // Act
        ResponseEntity<?> responseEntity = tvSeriesReviewController.addTvSeriesReview(tvSeriesReview);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(tvSeriesReview, responseEntity.getBody());
        assertEquals("username", tvSeriesReview.getUsername());
    }

    @Test
    void addTvSeriesReview_InvalidTvSeriesReview() {
        // Arrange
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        // Populate tvSeriesReview with invalid test data

        doThrow(IllegalArgumentException.class).when(tvSeriesReviewService).addTvSeriesReview(tvSeriesReview);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> tvSeriesReviewController.addTvSeriesReview(tvSeriesReview));
    }

    @Test
    void updateTvSeriesReview_ValidIdAndTvSeriesReview() {
        // Arrange
        int id = 1;
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        // Populate tvSeriesReview with test data

        when(tvSeriesReviewService.updateTvSeriesReview(id, tvSeriesReview)).thenReturn(tvSeriesReview);

        // Act
        ResponseEntity<?> responseEntity = tvSeriesReviewController.updateTvSeriesReview(id, tvSeriesReview);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tvSeriesReview, responseEntity.getBody());
    }

    @Test
    void updateTvSeriesReview_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        // Populate tvSeriesReview with test data

        when(tvSeriesReviewService.updateTvSeriesReview(id, tvSeriesReview)).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = tvSeriesReviewController.updateTvSeriesReview(id, tvSeriesReview);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateTvSeriesReview_InvalidTvSeriesReview() {
        // Arrange
        int id = 1;
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        // Populate tvSeriesReview with invalid test data

        doThrow(IllegalArgumentException.class).when(tvSeriesReviewService).updateTvSeriesReview(id, tvSeriesReview);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> tvSeriesReviewController.updateTvSeriesReview(id, tvSeriesReview));
    }

    @Test
    void deleteTvSeriesReview_ValidId() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<Void> responseEntity = tvSeriesReviewController.deleteTvSeriesReview(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(tvSeriesReviewService, times(1)).deleteTvSeriesReview(id);
    }

    @Test
    void deleteTvSeriesReview_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID

        // Act
        ResponseEntity<Void> responseEntity = tvSeriesReviewController.deleteTvSeriesReview(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(tvSeriesReviewService, never()).deleteTvSeriesReview(id);
    }
}

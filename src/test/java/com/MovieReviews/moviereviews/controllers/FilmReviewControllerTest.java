package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.reviews.FilmReviewController;
import com.MovieReviews.moviereviews.model.reviews.FilmReview;
import com.MovieReviews.moviereviews.service.reviews.FilmReviewService;
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

class FilmReviewControllerTest {

    @Mock
    private FilmReviewService filmReviewService;

    @InjectMocks
    private FilmReviewController filmReviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFilmReviews() {
        // Arrange
        List<FilmReview> filmReviews = new ArrayList<>();
        // Populate filmReviews with test data

        when(filmReviewService.getAllFilmReviews()).thenReturn(filmReviews);

        // Act
        ResponseEntity<List<FilmReview>> responseEntity = filmReviewController.getAllFilmReviews();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filmReviews, responseEntity.getBody());
    }

    @Test
    void getFilmReviewById_ExistingId() {
        // Arrange
        int id = 1;
        FilmReview filmReview = new FilmReview();
        // Populate filmReview with test data

        when(filmReviewService.getFilmReviewById(id)).thenReturn(filmReview);

        // Act
        ResponseEntity<FilmReview> responseEntity = filmReviewController.getFilmReviewById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filmReview, responseEntity.getBody());
    }

    @Test
    void getFilmReviewById_NonExistingId() {
        // Arrange
        int id = 999; // Non-existing ID

        when(filmReviewService.getFilmReviewById(id)).thenReturn(null);

        // Act
        ResponseEntity<FilmReview> responseEntity = filmReviewController.getFilmReviewById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void addFilmReview_ValidFilmReview() {
        // Arrange
        FilmReview filmReview = new FilmReview();
        // Populate filmReview with test data

        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);

        when(filmReviewService.addFilmReview(filmReview)).thenReturn(filmReview);

        // Act
        ResponseEntity<?> responseEntity = filmReviewController.addFilmReview(filmReview);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(filmReview, responseEntity.getBody());
        assertEquals("username", filmReview.getUsername());
    }

    @Test
    void addFilmReview_InvalidFilmReview() {
        // Arrange
        FilmReview filmReview = new FilmReview();
        // Populate filmReview with invalid test data

        doThrow(IllegalArgumentException.class).when(filmReviewService).addFilmReview(filmReview);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> filmReviewController.addFilmReview(filmReview));
    }

    @Test
    void updateFilmReview_ValidIdAndFilmReview() {
        // Arrange
        int id = 1;
        FilmReview filmReview = new FilmReview();
        // Populate filmReview with test data

        when(filmReviewService.updateFilmReview(id, filmReview)).thenReturn(filmReview);

        // Act
        ResponseEntity<?> responseEntity = filmReviewController.updateFilmReview(id, filmReview);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filmReview, responseEntity.getBody());
    }

    @Test
    void updateFilmReview_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID
        FilmReview filmReview = new FilmReview();
        // Populate filmReview with test data

        when(filmReviewService.updateFilmReview(id, filmReview)).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = filmReviewController.updateFilmReview(id, filmReview);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateFilmReview_InvalidFilmReview() {
        // Arrange
        int id = 1;
        FilmReview filmReview = new FilmReview();
        // Populate filmReview with invalid test data

        doThrow(IllegalArgumentException.class).when(filmReviewService).updateFilmReview(id, filmReview);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> filmReviewController.updateFilmReview(id, filmReview));
    }

    @Test
    void deleteFilmReview_ValidId() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<Void> responseEntity = filmReviewController.deleteFilmReview(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(filmReviewService, times(1)).deleteFilmReview(id);
    }

    @Test
    void deleteFilmReview_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID

        // Act
        ResponseEntity<Void> responseEntity = filmReviewController.deleteFilmReview(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(filmReviewService, never()).deleteFilmReview(id);
    }
}

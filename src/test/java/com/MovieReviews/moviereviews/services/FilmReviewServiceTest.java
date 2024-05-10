package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.reviews.FilmReview;
import com.MovieReviews.moviereviews.repositories.reviews.FilmReviewRepository;
import com.MovieReviews.moviereviews.service.reviews.FilmReviewService;
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

class FilmReviewServiceTest {

    @Mock
    private FilmReviewRepository filmReviewRepository;

    @InjectMocks
    private FilmReviewService filmReviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFilmReviews() {
        // Arrange
        List<FilmReview> filmReviews = new ArrayList<>();
        // Populate filmReviews with test data
        when(filmReviewRepository.findAll()).thenReturn(filmReviews);

        // Act
        List<FilmReview> result = filmReviewService.getAllFilmReviews();

        // Assert
        assertEquals(filmReviews, result);
    }

    @Test
    void getFilmReviewById_ExistingId_ReturnsFilmReview() {
        // Arrange
        int id = 1;
        FilmReview filmReview = new FilmReview();
        // Populate filmReview with test data
        when(filmReviewRepository.findById(id)).thenReturn(Optional.of(filmReview));

        // Act
        FilmReview result = filmReviewService.getFilmReviewById(id);

        // Assert
        assertEquals(filmReview, result);
    }

    @Test
    void getFilmReviewById_NonExistingId_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        when(filmReviewRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        FilmReview result = filmReviewService.getFilmReviewById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void addFilmReview_ValidFilmReview_ReturnsAddedFilmReview() {
        // Arrange
        FilmReview filmReview = new FilmReview();
        // Populate filmReview with test data
        when(filmReviewRepository.save(filmReview)).thenReturn(filmReview);

        // Act
        FilmReview result = filmReviewService.addFilmReview(filmReview);

        // Assert
        assertEquals(filmReview, result);
    }

    @Test
    void updateFilmReview_ExistingFilmReview_ReturnsUpdatedFilmReview() {
        // Arrange
        int id = 1;
        FilmReview existingFilmReview = new FilmReview();
        // Populate existingFilmReview with test data
        FilmReview updatedFilmReview = new FilmReview();
        // Populate updatedFilmReview with test data
        when(filmReviewRepository.findById(id)).thenReturn(Optional.of(existingFilmReview));
        when(filmReviewRepository.save(existingFilmReview)).thenReturn(updatedFilmReview);

        // Act
        FilmReview result = filmReviewService.updateFilmReview(id, existingFilmReview);

        // Assert
        assertEquals(updatedFilmReview, result);
    }

    @Test
    void updateFilmReview_NonExistingFilmReview_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        FilmReview nonExistingFilmReview = new FilmReview();
        // Populate nonExistingFilmReview with test data
        when(filmReviewRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        FilmReview result = filmReviewService.updateFilmReview(id, nonExistingFilmReview);

        // Assert
        assertNull(result);
    }

    @Test
    void deleteFilmReview_ValidId_DeletesFilmReview() {
        // Arrange
        int id = 1;

        // Act
        filmReviewService.deleteFilmReview(id);

        // Assert
        verify(filmReviewRepository, times(1)).deleteById(id);
    }
}

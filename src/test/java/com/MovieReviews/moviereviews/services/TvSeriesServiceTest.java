package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.repositories.TvSeriesRepository;
import com.MovieReviews.moviereviews.service.TvSeriesService;
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

class TvSeriesServiceTest {

    @Mock
    private TvSeriesRepository tvSeriesRepository;

    @InjectMocks
    private TvSeriesService tvSeriesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTvSeries() {
        // Arrange
        List<TvSeries> tvSeries = new ArrayList<>();
        // Populate tvSeries with test data
        when(tvSeriesRepository.findAll()).thenReturn(tvSeries);

        // Act
        List<TvSeries> result = tvSeriesService.getAllTvSeries();

        // Assert
        assertEquals(tvSeries, result);
    }

    @Test
    void getTvSeriesById_ExistingId_ReturnsTvSeries() {
        // Arrange
        int id = 1;
        TvSeries tvSeries = new TvSeries();
        // Populate tvSeries with test data
        when(tvSeriesRepository.findById(id)).thenReturn(Optional.of(tvSeries));

        // Act
        TvSeries result = tvSeriesService.getTvSeriesById(id);

        // Assert
        assertEquals(tvSeries, result);
    }

    @Test
    void getTvSeriesById_NonExistingId_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        when(tvSeriesRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        TvSeries result = tvSeriesService.getTvSeriesById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void addTvSeries_ValidTvSeries_ReturnsAddedTvSeries() {
        // Arrange
        TvSeries tvSeries = new TvSeries();
        // Populate tvSeries with test data
        when(tvSeriesRepository.save(tvSeries)).thenReturn(tvSeries);

        // Act
        TvSeries result = tvSeriesService.addTvSeries(tvSeries);

        // Assert
        assertEquals(tvSeries, result);
    }

    @Test
    void updateTvSeries_ExistingTvSeries_ReturnsUpdatedTvSeries() {
        // Arrange
        int id = 1;
        TvSeries existingTvSeries = new TvSeries();
        // Populate existingTvSeries with test data
        TvSeries updatedTvSeries = new TvSeries();
        // Populate updatedTvSeries with test data
        when(tvSeriesRepository.findById(id)).thenReturn(Optional.of(existingTvSeries));
        when(tvSeriesRepository.save(existingTvSeries)).thenReturn(updatedTvSeries);

        // Act
        TvSeries result = tvSeriesService.updateTvSeries(id, existingTvSeries);

        // Assert
        assertEquals(updatedTvSeries, result);
    }

    @Test
    void updateTvSeries_NonExistingTvSeries_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        TvSeries nonExistingTvSeries = new TvSeries();
        // Populate nonExistingTvSeries with test data
        when(tvSeriesRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        TvSeries result = tvSeriesService.updateTvSeries(id, nonExistingTvSeries);

        // Assert
        assertNull(result);
    }

    @Test
    void deleteTvSeries_ValidId_DeletesTvSeries() {
        // Arrange
        int id = 1;

        // Act
        tvSeriesService.deleteTvSeries(id);

        // Assert
        verify(tvSeriesRepository, times(1)).deleteById(id);
    }
}

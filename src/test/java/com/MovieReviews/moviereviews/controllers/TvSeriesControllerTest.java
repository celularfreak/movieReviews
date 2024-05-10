package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.TvSeriesController;
import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.service.TvSeriesService;
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

class TvSeriesControllerTest {

    @Mock
    private TvSeriesService tvSeriesService;

    @InjectMocks
    private TvSeriesController tvSeriesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTvSeries() {
        // Arrange
        List<TvSeries> tvSeriesList = new ArrayList<>();
        // Populate tvSeriesList with test data

        when(tvSeriesService.getAllTvSeries()).thenReturn(tvSeriesList);

        // Act
        ResponseEntity<List<TvSeries>> responseEntity = tvSeriesController.getAllTvSeries();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tvSeriesList, responseEntity.getBody());
    }

    @Test
    void getTvSeriesById_ExistingId() {
        // Arrange
        int id = 1;
        TvSeries tvSeries = new TvSeries();
        // Populate tvSeries with test data

        when(tvSeriesService.getTvSeriesById(id)).thenReturn(tvSeries);

        // Act
        ResponseEntity<TvSeries> responseEntity = tvSeriesController.getTvSeriesById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tvSeries, responseEntity.getBody());
    }

    @Test
    void getTvSeriesById_NonExistingId() {
        // Arrange
        int id = 999; // Non-existing ID

        when(tvSeriesService.getTvSeriesById(id)).thenReturn(null);

        // Act
        ResponseEntity<TvSeries> responseEntity = tvSeriesController.getTvSeriesById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void addTvSeries_ValidTvSeries() {
        // Arrange
        TvSeries tvSeries = new TvSeries();
        // Populate tvSeries with test data

        when(tvSeriesService.addTvSeries(tvSeries)).thenReturn(tvSeries);

        // Act
        ResponseEntity<?> responseEntity = tvSeriesController.addTvSeries(tvSeries);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(tvSeries, responseEntity.getBody());
    }

    @Test
    void addTvSeries_InvalidTvSeries() {
        // Arrange
        TvSeries tvSeries = new TvSeries();
        // Populate tvSeries with invalid test data

        doThrow(IllegalArgumentException.class).when(tvSeriesService).addTvSeries(tvSeries);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> tvSeriesController.addTvSeries(tvSeries));
    }

    @Test
    void updateTvSeries_ValidIdAndTvSeries() {
        // Arrange
        int id = 1;
        TvSeries tvSeries = new TvSeries();
        // Populate tvSeries with test data

        when(tvSeriesService.updateTvSeries(id, tvSeries)).thenReturn(tvSeries);

        // Act
        ResponseEntity<?> responseEntity = tvSeriesController.updateTvSeries(id, tvSeries);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tvSeries, responseEntity.getBody());
    }

    @Test
    void updateTvSeries_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID
        TvSeries tvSeries = new TvSeries();
        // Populate tvSeries with test data

        when(tvSeriesService.updateTvSeries(id, tvSeries)).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = tvSeriesController.updateTvSeries(id, tvSeries);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateTvSeries_InvalidTvSeries() {
        // Arrange
        int id = 1;
        TvSeries tvSeries = new TvSeries();
        // Populate tvSeries with invalid test data

        doThrow(IllegalArgumentException.class).when(tvSeriesService).updateTvSeries(id, tvSeries);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> tvSeriesController.updateTvSeries(id, tvSeries));
    }

    @Test
    void deleteTvSeries_ValidId() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<Void> responseEntity = tvSeriesController.deleteTvSeries(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(tvSeriesService, times(1)).deleteTvSeries(id);
    }

    @Test
    void deleteTvSeries_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID

        // Act
        ResponseEntity<Void> responseEntity = tvSeriesController.deleteTvSeries(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(tvSeriesService, never()).deleteTvSeries(id);
    }
}

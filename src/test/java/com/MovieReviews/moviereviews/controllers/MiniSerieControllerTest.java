package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.series.MiniSerieController;
import com.MovieReviews.moviereviews.model.series.MiniSerie;
import com.MovieReviews.moviereviews.service.series.MiniSerieService;
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

class MiniSerieControllerTest {

    @Mock
    private MiniSerieService miniSerieService;

    @InjectMocks
    private MiniSerieController miniSerieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMiniSeries() {
        // Arrange
        List<MiniSerie> miniSeries = new ArrayList<>();
        // Populate miniSeries with test data

        when(miniSerieService.getAllMiniSeries()).thenReturn(miniSeries);

        // Act
        ResponseEntity<List<MiniSerie>> responseEntity = miniSerieController.getAllMiniSeries();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(miniSeries, responseEntity.getBody());
    }

    @Test
    void getMiniSerieById_ExistingId() {
        // Arrange
        int id = 1;
        MiniSerie miniSerie = new MiniSerie();
        // Populate miniSerie with test data

        when(miniSerieService.getMiniSerieById(id)).thenReturn(miniSerie);

        // Act
        ResponseEntity<MiniSerie> responseEntity = miniSerieController.getMiniSerieById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(miniSerie, responseEntity.getBody());
    }

    @Test
    void getMiniSerieById_NonExistingId() {
        // Arrange
        int id = 999; // Non-existing ID

        when(miniSerieService.getMiniSerieById(id)).thenReturn(null);

        // Act
        ResponseEntity<MiniSerie> responseEntity = miniSerieController.getMiniSerieById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void addMiniSerie_ValidMiniSerie() {
        // Arrange
        MiniSerie miniSerie = new MiniSerie();
        // Populate miniSerie with test data

        when(miniSerieService.addMiniSerie(miniSerie)).thenReturn(miniSerie);

        // Act
        ResponseEntity<?> responseEntity = miniSerieController.addMiniSerie(miniSerie);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(miniSerie, responseEntity.getBody());
    }

    @Test
    void addMiniSerie_InvalidMiniSerie() {
        // Arrange
        MiniSerie miniSerie = new MiniSerie();
        // Populate miniSerie with invalid test data

        doThrow(IllegalArgumentException.class).when(miniSerieService).addMiniSerie(miniSerie);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> miniSerieController.addMiniSerie(miniSerie));
    }

    @Test
    void updateMiniSerie_ValidIdAndMiniSerie() {
        // Arrange
        int id = 1;
        MiniSerie miniSerie = new MiniSerie();
        // Populate miniSerie with test data

        when(miniSerieService.updateMiniSerie(id, miniSerie)).thenReturn(miniSerie);

        // Act
        ResponseEntity<?> responseEntity = miniSerieController.updateMiniSerie(id, miniSerie);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(miniSerie, responseEntity.getBody());
    }

    @Test
    void updateMiniSerie_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID
        MiniSerie miniSerie = new MiniSerie();
        // Populate miniSerie with test data

        when(miniSerieService.updateMiniSerie(id, miniSerie)).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = miniSerieController.updateMiniSerie(id, miniSerie);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateMiniSerie_InvalidMiniSerie() {
        // Arrange
        int id = 1;
        MiniSerie miniSerie = new MiniSerie();
        // Populate miniSerie with invalid test data

        doThrow(IllegalArgumentException.class).when(miniSerieService).updateMiniSerie(id, miniSerie);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> miniSerieController.updateMiniSerie(id, miniSerie));
    }

    @Test
    void deleteMiniSerie_ValidId() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<Void> responseEntity = miniSerieController.deleteMiniSerie(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(miniSerieService, times(1)).deleteMiniSerie(id);
    }

    @Test
    void deleteMiniSerie_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID

        // Act
        ResponseEntity<Void> responseEntity = miniSerieController.deleteMiniSerie(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(miniSerieService, never()).deleteMiniSerie(id);
    }
}

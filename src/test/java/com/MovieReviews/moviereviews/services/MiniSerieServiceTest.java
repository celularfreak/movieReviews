package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.series.MiniSerie;
import com.MovieReviews.moviereviews.repositories.series.MiniSerieRepository;
import com.MovieReviews.moviereviews.service.series.MiniSerieService;
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

class MiniSerieServiceTest {

    @Mock
    private MiniSerieRepository miniSerieRepository;

    @InjectMocks
    private MiniSerieService miniSerieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMiniSeries() {
        // Arrange
        List<MiniSerie> miniSeries = new ArrayList<>();
        // Populate miniSeries with test data
        when(miniSerieRepository.findAll()).thenReturn(miniSeries);

        // Act
        List<MiniSerie> result = miniSerieService.getAllMiniSeries();

        // Assert
        assertEquals(miniSeries, result);
    }

    @Test
    void getMiniSerieById_ExistingId_ReturnsMiniSerie() {
        // Arrange
        int id = 1;
        MiniSerie miniSerie = new MiniSerie();
        // Populate miniSerie with test data
        when(miniSerieRepository.findById(id)).thenReturn(Optional.of(miniSerie));

        // Act
        MiniSerie result = miniSerieService.getMiniSerieById(id);

        // Assert
        assertEquals(miniSerie, result);
    }

    @Test
    void getMiniSerieById_NonExistingId_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        when(miniSerieRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        MiniSerie result = miniSerieService.getMiniSerieById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void addMiniSerie_ValidMiniSerie_ReturnsAddedMiniSerie() {
        // Arrange
        MiniSerie miniSerie = new MiniSerie();
        // Populate miniSerie with test data
        when(miniSerieRepository.save(miniSerie)).thenReturn(miniSerie);

        // Act
        MiniSerie result = miniSerieService.addMiniSerie(miniSerie);

        // Assert
        assertEquals(miniSerie, result);
    }

    @Test
    void updateMiniSerie_ExistingMiniSerie_ReturnsUpdatedMiniSerie() {
        // Arrange
        int id = 1;
        MiniSerie existingMiniSerie = new MiniSerie();
        // Populate existingMiniSerie with test data
        MiniSerie updatedMiniSerie = new MiniSerie();
        // Populate updatedMiniSerie with test data
        when(miniSerieRepository.findById(id)).thenReturn(Optional.of(existingMiniSerie));
        when(miniSerieRepository.save(existingMiniSerie)).thenReturn(updatedMiniSerie);

        // Act
        MiniSerie result = miniSerieService.updateMiniSerie(id, existingMiniSerie);

        // Assert
        assertEquals(updatedMiniSerie, result);
    }

    @Test
    void updateMiniSerie_NonExistingMiniSerie_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        MiniSerie nonExistingMiniSerie = new MiniSerie();
        // Populate nonExistingMiniSerie with test data
        when(miniSerieRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        MiniSerie result = miniSerieService.updateMiniSerie(id, nonExistingMiniSerie);

        // Assert
        assertNull(result);
    }

    @Test
    void deleteMiniSerie_ValidId_DeletesMiniSerie() {
        // Arrange
        int id = 1;

        // Act
        miniSerieService.deleteMiniSerie(id);

        // Assert
        verify(miniSerieRepository, times(1)).deleteById(id);
    }
}

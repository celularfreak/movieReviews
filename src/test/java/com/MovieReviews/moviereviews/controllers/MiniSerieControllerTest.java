package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.MiniSerieController;
import com.MovieReviews.moviereviews.model.Series.MiniSerie;
import com.MovieReviews.moviereviews.service.MiniSerieService;
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
public class MiniSerieControllerTest {

    @Mock
    private MiniSerieService miniSerieService;

    @InjectMocks
    private MiniSerieController miniSerieController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllMiniSeries() {
        List<MiniSerie> miniSeries = new ArrayList<>();
        miniSeries.add(new MiniSerie(1L, "MiniSeries 1", LocalDate.parse("2024-01-01"), "Drama", 5, LocalDate.parse("2024-02-01")));
        miniSeries.add(new MiniSerie(2L, "MiniSeries 2", LocalDate.parse("2024-02-02"), "Comedy", 4, LocalDate.parse("2024-03-02")));
        when(miniSerieService.getAllMiniSeries()).thenReturn(miniSeries);

        ResponseEntity<List<MiniSerie>> responseEntity = miniSerieController.getAllMiniSeries();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetMiniSerieById() {
        MiniSerie miniSerie = new MiniSerie(1L, "MiniSeries 1", LocalDate.parse("2024-01-01"), "Drama", 5, LocalDate.parse("2024-02-01"));
        when(miniSerieService.getMiniSerieById(1L)).thenReturn(miniSerie);

        ResponseEntity<MiniSerie> responseEntity = miniSerieController.getMiniSerieById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("MiniSeries 1", responseEntity.getBody().getTitle());
        assertEquals(5, responseEntity.getBody().getNumberEpisodes());
    }

    @Test
    public void testAddMiniSerie() {
        MiniSerie miniSerie = new MiniSerie(1L, "MiniSeries 1", LocalDate.parse("2024-01-01"), "Drama", 5, LocalDate.parse("2024-02-01"));
        when(miniSerieService.addMiniSerie(any(MiniSerie.class))).thenReturn(miniSerie);

        ResponseEntity<MiniSerie> responseEntity = miniSerieController.addMiniSerie(miniSerie);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("MiniSeries 1", responseEntity.getBody().getTitle());
        assertEquals(5, responseEntity.getBody().getNumberEpisodes());
    }

    @Test
    public void testUpdateMiniSerie() {
        MiniSerie miniSerie = new MiniSerie(1L, "MiniSeries 1", LocalDate.parse("2024-01-01"), "Drama", 5, LocalDate.parse("2024-02-01"));
        when(miniSerieService.updateMiniSerie(anyLong(), any(MiniSerie.class))).thenReturn(miniSerie);

        ResponseEntity<MiniSerie> responseEntity = miniSerieController.updateMiniSerie(1L, miniSerie);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("MiniSeries 1", responseEntity.getBody().getTitle());
        assertEquals(5, responseEntity.getBody().getNumberEpisodes());
    }

    @Test
    public void testDeleteMiniSerie() {
        doNothing().when(miniSerieService).deleteMiniSerie(1L);

        ResponseEntity<Void> responseEntity = miniSerieController.deleteMiniSerie(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}

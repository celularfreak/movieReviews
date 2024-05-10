package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.TvSeriesController;
import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.service.TvSeriesService;
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
public class TvSeriesControllerTest {

    @Mock
    private TvSeriesService tvSeriesService;

    @InjectMocks
    private TvSeriesController tvSeriesController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllTvSeries() {
        List<TvSeries> tvSeriesList = new ArrayList<>();
        tvSeriesList.add(new TvSeries(1L, "series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01")));
        tvSeriesList.add(new TvSeries(2L, "series 2", LocalDate.parse("2024-02-02"), "Comedy", 4, 15, LocalDate.parse("2025-02-02")));
        when(tvSeriesService.getAllTvSeries()).thenReturn(tvSeriesList);

        ResponseEntity<List<TvSeries>> responseEntity = tvSeriesController.getAllTvSeries();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetTvSeriesById() {
        TvSeries tvSeries = new TvSeries(1L, "series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01"));
        when(tvSeriesService.getTvSeriesById(1L)).thenReturn(tvSeries);

        ResponseEntity<TvSeries> responseEntity = tvSeriesController.getTvSeriesById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("series 1", responseEntity.getBody().getTitle());
        assertEquals(5, responseEntity.getBody().getNumberSeasons());
    }

    @Test
    public void testAddTvSeries() {
        TvSeries tvSeries = new TvSeries(1L, "series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01"));
        when(tvSeriesService.addTvSeries(any(TvSeries.class))).thenReturn(tvSeries);

        ResponseEntity<TvSeries> responseEntity = tvSeriesController.addTvSeries(tvSeries);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("series 1", responseEntity.getBody().getTitle());
        assertEquals(20, responseEntity.getBody().getNumberEpisodes());
    }

    @Test
    public void testUpdateTvSeries() {
        TvSeries tvSeries = new TvSeries(1L, "series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01"));
        when(tvSeriesService.updateTvSeries(anyLong(), any(TvSeries.class))).thenReturn(tvSeries);

        ResponseEntity<TvSeries> responseEntity = tvSeriesController.updateTvSeries(1L, tvSeries);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("series 1", responseEntity.getBody().getTitle());
        assertEquals(5, responseEntity.getBody().getNumberSeasons());
    }

    @Test
    public void testDeleteTvSeries() {
        doNothing().when(tvSeriesService).deleteTvSeries(1L);

        ResponseEntity<Void> responseEntity = tvSeriesController.deleteTvSeries(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(tvSeriesService, times(1)).deleteTvSeries(1L);
    }
}

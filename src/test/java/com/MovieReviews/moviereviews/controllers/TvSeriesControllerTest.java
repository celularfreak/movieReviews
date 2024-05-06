package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.dto.TvSeriesDTO;
import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.service.TvSeriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private TvSeriesControllerTest tvSeriesController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllTvSeries() {
        List<TvSeries> tvSeriesList = new ArrayList<>();
        tvSeriesList.add(new TvSeries(1L, "Series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01")));
        tvSeriesList.add(new TvSeries(2L, "Series 2", LocalDate.parse("2024-02-02"), "Comedy", 4, 15, LocalDate.parse("2025-02-02")));
        when(tvSeriesService.getAllTvSeries()).thenReturn(tvSeriesList);

        List<TvSeriesDTO> result = tvSeriesController.getAllTvSeries().getBody();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetTvSeriesById() {
        TvSeries tvSeries = new TvSeries(1L, "Series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01"));
        when(tvSeriesService.getTvSeriesById(1L)).thenReturn(tvSeries);

        TvSeriesDTO result = tvSeriesController.getTvSeriesById(1L).getBody();

        assertEquals("Series 1", result.getTitle());
        assertEquals(5, result.getNumberSeasons());
    }

    @Test
    public void testAddTvSeries() {
        TvSeriesDTO tvSeriesDTO = new TvSeriesDTO("Series 1", "2024-01-01", "Action", 5, 20, "2025-01-01");
        TvSeries tvSeries = new TvSeries(1L, "Series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01"));
        when(tvSeriesService.addTvSeries(any(TvSeries.class))).thenReturn(tvSeries);

        TvSeriesDTO result = tvSeriesController.addTvSeries(tvSeriesDTO).getBody();

        assertEquals("Series 1", result.getTitle());
        assertEquals(20, result.getNumberEpisodes());
    }

    @Test
    public void testUpdateTvSeries() {
        TvSeriesDTO tvSeriesDTO = new TvSeriesDTO("Series 1", "2024-01-01", "Action", 5, 20, "2025-01-01");
        TvSeries tvSeries = new TvSeries(1L, "Series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01"));
        when(tvSeriesService.updateTvSeries(anyLong(), any(TvSeries.class))).thenReturn(tvSeries);

        TvSeriesDTO result = tvSeriesController.updateTvSeries(1L, tvSeriesDTO).getBody();

        assertEquals("Series 1", result.getTitle());
        assertEquals(5, result.getNumberSeasons());
    }

    @Test
    public void testDeleteTvSeries() {
        doNothing().when(tvSeriesService).deleteTvSeries(1L);

        tvSeriesController.deleteTvSeries(1L);

        verify(tvSeriesService, times(1)).deleteTvSeries(1L);
    }
}

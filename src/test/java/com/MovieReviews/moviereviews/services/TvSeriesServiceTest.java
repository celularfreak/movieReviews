package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.repositories.TvSeriesRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TvSeriesServiceTest {

    @Mock
    private TvSeriesRepository tvSeriesRepository;

    @InjectMocks
    private TvSeriesService tvSeriesService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllTvSeries() {
        List<TvSeries> tvSeriesList = new ArrayList<>();
        tvSeriesList.add(new TvSeries(1L, "Series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01")));
        tvSeriesList.add(new TvSeries(2L, "Series 2", LocalDate.parse("2024-02-02"), "Comedy", 4, 15, LocalDate.parse("2025-02-02")));
        when(tvSeriesRepository.findAll()).thenReturn(tvSeriesList);

        List<TvSeries> result = tvSeriesService.getAllTvSeries();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetTvSeriesById() {
        TvSeries tvSeries = new TvSeries(1L, "Series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01"));
        when(tvSeriesRepository.findById(1L)).thenReturn(Optional.of(tvSeries));

        TvSeries result = tvSeriesService.getTvSeriesById(1L);

        assertEquals("Series 1", result.getTitle());
        assertEquals(5, result.getNumberSeasons());
    }

    @Test
    public void testAddTvSeries() {
        TvSeries tvSeries = new TvSeries(1L, "Series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01"));
        when(tvSeriesRepository.save(any(TvSeries.class))).thenReturn(tvSeries);

        TvSeries result = tvSeriesService.addTvSeries(tvSeries);

        assertEquals("Series 1", result.getTitle());
        assertEquals(20, result.getNumberEpisodes());
    }

    @Test
    public void testUpdateTvSeries() {
        TvSeries tvSeries = new TvSeries(1L, "Series 1", LocalDate.parse("2024-01-01"), "Action", 5, 20, LocalDate.parse("2025-01-01"));
        when(tvSeriesRepository.findById(1L)).thenReturn(Optional.of(tvSeries));
        when(tvSeriesRepository.save(any(TvSeries.class))).thenReturn(tvSeries);

        TvSeries result = tvSeriesService.updateTvSeries(1L, tvSeries);

        assertEquals("Series 1", result.getTitle());
        assertEquals(5, result.getNumberSeasons());
    }

    @Test
    public void testDeleteTvSeries() {
        doNothing().when(tvSeriesRepository).deleteById(1L);

        tvSeriesService.deleteTvSeries(1L);

        verify(tvSeriesRepository, times(1)).deleteById(1L);
    }
}

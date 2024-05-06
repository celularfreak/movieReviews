package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.Series.MiniSerie;
import com.MovieReviews.moviereviews.repositories.MiniSerieRepository;
import com.MovieReviews.moviereviews.service.MiniSerieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MiniSerieServiceTest {

    @Mock
    private MiniSerieRepository miniSerieRepository;

    @InjectMocks
    private MiniSerieService miniSerieService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllMiniSeries() {
        List<MiniSerie> miniSeries = new ArrayList<>();
        miniSeries.add(new MiniSerie(1L, "MiniSeries 1", LocalDate.parse("2024-01-01"), "Drama", 5, LocalDate.parse("2024-02-01")));
        miniSeries.add(new MiniSerie(2L, "MiniSeries 2", LocalDate.parse("2024-02-02"), "Comedy", 4, LocalDate.parse("2024-03-02")));
        when(miniSerieRepository.findAll()).thenReturn(miniSeries);

        List<MiniSerie> result = miniSerieService.getAllMiniSeries();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetMiniSerieById() {
        MiniSerie miniSerie = new MiniSerie(1L, "MiniSeries 1", LocalDate.parse("2024-01-01"), "Drama", 5, LocalDate.parse("2024-02-01"));
        when(miniSerieRepository.findById(1L)).thenReturn(Optional.of(miniSerie));

        MiniSerie result = miniSerieService.getMiniSerieById(1L);

        assertEquals("MiniSeries 1", result.getTitle());
        assertEquals("Drama", result.getGenre());
    }

    @Test
    public void testAddMiniSerie() {
        MiniSerie miniSerie = new MiniSerie(1L, "MiniSeries 1", LocalDate.parse("2024-01-01"), "Drama", 5, LocalDate.parse("2024-02-01"));
        when(miniSerieRepository.save(any(MiniSerie.class))).thenReturn(miniSerie);

        MiniSerie result = miniSerieService.addMiniSerie(miniSerie);

        assertEquals("MiniSeries 1", result.getTitle());
        assertEquals("Drama", result.getGenre());
    }

    @Test
    public void testUpdateMiniSerie() {
        MiniSerie miniSerie = new MiniSerie(1L, "MiniSeries 1", LocalDate.parse("2024-01-01"), "Drama", 5, LocalDate.parse("2024-02-01"));
        when(miniSerieRepository.findById(1L)).thenReturn(Optional.of(miniSerie));
        when(miniSerieRepository.save(any(MiniSerie.class))).thenReturn(miniSerie);

        MiniSerie updatedMiniSerie = new MiniSerie(1L, "MiniSeries 1", LocalDate.parse("2024-01-01"), "Drama", 8, LocalDate.parse("2024-02-01"));
        MiniSerie result = miniSerieService.updateMiniSerie(1L, updatedMiniSerie);

        assertEquals(8, result.getNumberEpisodes());
    }

    @Test
    public void testDeleteMiniSerie() {
        doNothing().when(miniSerieRepository).deleteById(1L);

        miniSerieService.deleteMiniSerie(1L);

        verify(miniSerieRepository, times(1)).deleteById(1L);
    }
}

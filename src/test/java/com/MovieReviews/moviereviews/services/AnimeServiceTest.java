package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.series.Anime;
import com.MovieReviews.moviereviews.repositories.series.AnimeRepository;
import com.MovieReviews.moviereviews.service.series.AnimeService;
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
public class AnimeServiceTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeService animeService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllAnimes() {
        List<Anime> animeList = new ArrayList<>();
        animeList.add(new Anime(1L, "Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A"));
        animeList.add(new Anime(2L, "Anime 2", LocalDate.parse("2024-02-02"), "Comedy", 2, 24, LocalDate.parse("2024-04-02"), "Studio B"));
        when(animeRepository.findAll()).thenReturn(animeList);

        List<Anime> result = animeService.getAllAnimes();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetAnimeById() {
        Anime anime = new Anime(1L, "Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A");
        when(animeRepository.findById(1L)).thenReturn(Optional.of(anime));

        Anime result = animeService.getAnimeById(1L);

        assertEquals("Anime 1", result.getTitle());
        assertEquals(12, result.getNumberEpisodes());
    }

    @Test
    public void testAddAnime() {
        Anime anime = new Anime(1L, "Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A");
        when(animeRepository.save(any(Anime.class))).thenReturn(anime);

        Anime result = animeService.addAnime(anime);

        assertEquals("Anime 1", result.getTitle());
        assertEquals(12, result.getNumberEpisodes());
    }

    @Test
    public void testUpdateAnime() {
        Anime anime = new Anime(1L, "Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A");
        when(animeRepository.findById(1L)).thenReturn(Optional.of(anime));
        when(animeRepository.save(any(Anime.class))).thenReturn(anime);

        Anime result = animeService.updateAnime(1L, anime);

        assertEquals("Anime 1", result.getTitle());
        assertEquals(12, result.getNumberEpisodes());
    }

    @Test
    public void testDeleteAnime() {
        doNothing().when(animeRepository).deleteById(1L);

        animeService.deleteAnime(1L);

        verify(animeRepository, times(1)).deleteById(1L);
    }
}

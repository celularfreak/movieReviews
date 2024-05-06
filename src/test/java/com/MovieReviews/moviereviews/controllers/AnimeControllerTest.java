package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.AnimeController;
import com.MovieReviews.moviereviews.dto.AnimeDTO;
import com.MovieReviews.moviereviews.model.Series.Anime;
import com.MovieReviews.moviereviews.service.AnimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimeControllerTest {

    @Mock
    private AnimeService animeService;

    @InjectMocks
    private AnimeController animeController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllAnimes() {
        List<Anime> animeList = new ArrayList<>();
        animeList.add(new Anime(1L, "Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A"));
        animeList.add(new Anime(2L, "Anime 2", LocalDate.parse("2024-02-02"), "Comedy", 2, 24, LocalDate.parse("2024-04-02"), "Studio B"));
        when(animeService.getAllAnimes()).thenReturn(animeList);

        ResponseEntity<List<AnimeDTO>> responseEntity = animeController.getAllAnimes();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetAnimeById() {
        Anime anime = new Anime(1L, "Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A");
        when(animeService.getAnimeById(1L)).thenReturn(anime);

        ResponseEntity<AnimeDTO> responseEntity = animeController.getAnimeById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Anime 1", responseEntity.getBody().getTitle());
        assertEquals(12, responseEntity.getBody().getNumberEpisodes());
    }

    @Test
    public void testAddAnime() {
        AnimeDTO animeDTO = new AnimeDTO("Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A");
        Anime anime = new Anime(1L, "Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A");
        when(animeService.addAnime(any(Anime.class))).thenReturn(anime);

        ResponseEntity<AnimeDTO> responseEntity = animeController.addAnime(animeDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Anime 1", responseEntity.getBody().getTitle());
        assertEquals(12, responseEntity.getBody().getNumberEpisodes());
    }

    @Test
    public void testUpdateAnime() {
        AnimeDTO animeDTO = new AnimeDTO("Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A");
        Anime anime = new Anime(1L, "Anime 1", LocalDate.parse("2024-01-01"), "Action", 1, 12, LocalDate.parse("2024-03-01"), "Studio A");
        when(animeService.updateAnime(anyLong(), any(Anime.class))).thenReturn(anime);

        ResponseEntity<AnimeDTO> responseEntity = animeController.updateAnime(1L, animeDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Anime 1", responseEntity.getBody().getTitle());
        assertEquals(12, responseEntity.getBody().getNumberEpisodes());
    }

    @Test
    public void testDeleteAnime() {
        ResponseEntity<Void> responseEntity = animeController.deleteAnime(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(animeService, times(1)).deleteAnime(1L);
    }
}

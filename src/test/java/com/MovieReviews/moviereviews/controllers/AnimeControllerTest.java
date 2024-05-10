package com.MovieReviews.moviereviews.controller.series;

import com.MovieReviews.moviereviews.model.series.Anime;
import com.MovieReviews.moviereviews.service.series.AnimeService;
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

class AnimeControllerTest {

    @Mock
    private AnimeService animeService;

    @InjectMocks
    private AnimeController animeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAnimes() {
        // Arrange
        List<Anime> animes = new ArrayList<>();
        // Populate animes with test data

        when(animeService.getAllAnimes()).thenReturn(animes);

        // Act
        ResponseEntity<List<Anime>> responseEntity = animeController.getAllAnimes();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(animes, responseEntity.getBody());
    }

    @Test
    void getAnimeById_ExistingId() {
        // Arrange
        int id = 1;
        Anime anime = new Anime();
        // Populate anime with test data

        when(animeService.getAnimeById(id)).thenReturn(anime);

        // Act
        ResponseEntity<Anime> responseEntity = animeController.getAnimeById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(anime, responseEntity.getBody());
    }

    @Test
    void getAnimeById_NonExistingId() {
        // Arrange
        int id = 999; // Non-existing ID

        when(animeService.getAnimeById(id)).thenReturn(null);

        // Act
        ResponseEntity<Anime> responseEntity = animeController.getAnimeById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void addAnime_ValidAnime() {
        // Arrange
        Anime anime = new Anime();
        // Populate anime with test data

        when(animeService.addAnime(anime)).thenReturn(anime);

        // Act
        ResponseEntity<?> responseEntity = animeController.addAnime(anime);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(anime, responseEntity.getBody());
    }

    @Test
    void addAnime_InvalidAnime() {
        // Arrange
        Anime anime = new Anime();
        // Populate anime with invalid test data

        doThrow(IllegalArgumentException.class).when(animeService).addAnime(anime);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> animeController.addAnime(anime));
    }

    @Test
    void updateAnime_ValidIdAndAnime() {
        // Arrange
        int id = 1;
        Anime anime = new Anime();
        // Populate anime with test data

        when(animeService.updateAnime(id, anime)).thenReturn(anime);

        // Act
        ResponseEntity<?> responseEntity = animeController.updateAnime(id, anime);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(anime, responseEntity.getBody());
    }

    @Test
    void updateAnime_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID
        Anime anime = new Anime();
        // Populate anime with test data

        when(animeService.updateAnime(id, anime)).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = animeController.updateAnime(id, anime);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateAnime_InvalidAnime() {
        // Arrange
        int id = 1;
        Anime anime = new Anime();
        // Populate anime with invalid test data

        doThrow(IllegalArgumentException.class).when(animeService).updateAnime(id, anime);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> animeController.updateAnime(id, anime));
    }

    @Test
    void deleteAnime_ValidId() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<Void> responseEntity = animeController.deleteAnime(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(animeService, times(1)).deleteAnime(id);
    }

    @Test
    void deleteAnime_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID

        // Act
        ResponseEntity<Void> responseEntity = animeController.deleteAnime(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(animeService, never()).deleteAnime(id);
    }
}

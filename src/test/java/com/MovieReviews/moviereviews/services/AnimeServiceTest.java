package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.series.Anime;
import com.MovieReviews.moviereviews.repositories.series.AnimeRepository;
import com.MovieReviews.moviereviews.service.series.AnimeService;
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

class AnimeServiceTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeService animeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAnimes() {
        // Arrange
        List<Anime> animes = new ArrayList<>();
        // Populate animes with test data
        when(animeRepository.findAll()).thenReturn(animes);

        // Act
        List<Anime> result = animeService.getAllAnimes();

        // Assert
        assertEquals(animes, result);
    }

    @Test
    void getAnimeById_ExistingId_ReturnsAnime() {
        // Arrange
        int id = 1;
        Anime anime = new Anime();
        // Populate anime with test data
        when(animeRepository.findById(id)).thenReturn(Optional.of(anime));

        // Act
        Anime result = animeService.getAnimeById(id);

        // Assert
        assertEquals(anime, result);
    }

    @Test
    void getAnimeById_NonExistingId_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        when(animeRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Anime result = animeService.getAnimeById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void addAnime_ValidAnime_ReturnsAddedAnime() {
        // Arrange
        Anime anime = new Anime();
        // Populate anime with test data
        when(animeRepository.save(anime)).thenReturn(anime);

        // Act
        Anime result = animeService.addAnime(anime);

        // Assert
        assertEquals(anime, result);
    }

    @Test
    void updateAnime_ExistingAnime_ReturnsUpdatedAnime() {
        // Arrange
        int id = 1;
        Anime existingAnime = new Anime();
        // Populate existingAnime with test data
        Anime updatedAnime = new Anime();
        // Populate updatedAnime with test data
        when(animeRepository.findById(id)).thenReturn(Optional.of(existingAnime));
        when(animeRepository.save(existingAnime)).thenReturn(updatedAnime);

        // Act
        Anime result = animeService.updateAnime(id, existingAnime);

        // Assert
        assertEquals(updatedAnime, result);
    }

    @Test
    void updateAnime_NonExistingAnime_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        Anime nonExistingAnime = new Anime();
        // Populate nonExistingAnime with test data
        when(animeRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Anime result = animeService.updateAnime(id, nonExistingAnime);

        // Assert
        assertNull(result);
    }

    @Test
    void deleteAnime_ValidId_DeletesAnime() {
        // Arrange
        int id = 1;

        // Act
        animeService.deleteAnime(id);

        // Assert
        verify(animeRepository, times(1)).deleteById(id);
    }
}

package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.model.Film;
import com.MovieReviews.moviereviews.repositories.FilmRepository;
import com.MovieReviews.moviereviews.service.FilmService;
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

class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFilms() {
        // Arrange
        List<Film> films = new ArrayList<>();
        // Populate films with test data
        when(filmRepository.findAll()).thenReturn(films);

        // Act
        List<Film> result = filmService.getAllFilms();

        // Assert
        assertEquals(films, result);
    }

    @Test
    void getFilmById_ExistingId_ReturnsFilm() {
        // Arrange
        int id = 1;
        Film film = new Film();
        // Populate film with test data
        when(filmRepository.findById(id)).thenReturn(Optional.of(film));

        // Act
        Film result = filmService.getFilmById(id);

        // Assert
        assertEquals(film, result);
    }

    @Test
    void getFilmById_NonExistingId_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        when(filmRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Film result = filmService.getFilmById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void addFilm_ValidFilm_ReturnsAddedFilm() {
        // Arrange
        Film film = new Film();
        // Populate film with test data
        when(filmRepository.save(film)).thenReturn(film);

        // Act
        Film result = filmService.addFilm(film);

        // Assert
        assertEquals(film, result);
    }

    @Test
    void updateFilm_ExistingFilm_ReturnsUpdatedFilm() {
        // Arrange
        int id = 1;
        Film existingFilm = new Film();
        // Populate existingFilm with test data
        Film updatedFilm = new Film();
        // Populate updatedFilm with test data
        when(filmRepository.findById(id)).thenReturn(Optional.of(existingFilm));
        when(filmRepository.save(existingFilm)).thenReturn(updatedFilm);

        // Act
        Film result = filmService.updateFilm(id, existingFilm);

        // Assert
        assertEquals(updatedFilm, result);
    }

    @Test
    void updateFilm_NonExistingFilm_ReturnsNull() {
        // Arrange
        int id = 999; // Non-existing ID
        Film nonExistingFilm = new Film();
        // Populate nonExistingFilm with test data
        when(filmRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Film result = filmService.updateFilm(id, nonExistingFilm);

        // Assert
        assertNull(result);
    }

    @Test
    void deleteFilm_ValidId_DeletesFilm() {
        // Arrange
        int id = 1;

        // Act
        filmService.deleteFilm(id);

        // Assert
        verify(filmRepository, times(1)).deleteById(id);
    }
}

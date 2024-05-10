package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.FilmController;
import com.MovieReviews.moviereviews.model.Film;
import com.MovieReviews.moviereviews.service.FilmService;
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

class FilmControllerTest {

    @Mock
    private FilmService filmService;

    @InjectMocks
    private FilmController filmController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFilms() {
        // Arrange
        List<Film> films = new ArrayList<>();
        // Populate films with test data

        when(filmService.getAllFilms()).thenReturn(films);

        // Act
        ResponseEntity<List<Film>> responseEntity = filmController.getAllFilms();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(films, responseEntity.getBody());
    }

    @Test
    void getFilmById_ExistingId() {
        // Arrange
        int id = 1;
        Film film = new Film();
        // Populate film with test data

        when(filmService.getFilmById(id)).thenReturn(film);

        // Act
        ResponseEntity<Film> responseEntity = filmController.getFilmById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(film, responseEntity.getBody());
    }

    @Test
    void getFilmById_NonExistingId() {
        // Arrange
        int id = 999; // Non-existing ID

        when(filmService.getFilmById(id)).thenReturn(null);

        // Act
        ResponseEntity<Film> responseEntity = filmController.getFilmById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void addFilm_ValidFilm() {
        // Arrange
        Film film = new Film();
        // Populate film with test data

        when(filmService.addFilm(film)).thenReturn(film);

        // Act
        ResponseEntity<?> responseEntity = filmController.addFilm(film);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(film, responseEntity.getBody());
    }

    @Test
    void addFilm_InvalidFilm() {
        // Arrange
        Film film = new Film();
        // Populate film with invalid test data

        doThrow(IllegalArgumentException.class).when(filmService).addFilm(film);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> filmController.addFilm(film));
    }

    @Test
    void updateFilm_ValidIdAndFilm() {
        // Arrange
        int id = 1;
        Film film = new Film();
        // Populate film with test data

        when(filmService.updateFilm(id, film)).thenReturn(film);

        // Act
        ResponseEntity<?> responseEntity = filmController.updateFilm(id, film);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(film, responseEntity.getBody());
    }

    @Test
    void updateFilm_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID
        Film film = new Film();
        // Populate film with test data

        when(filmService.updateFilm(id, film)).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = filmController.updateFilm(id, film);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateFilm_InvalidFilm() {
        // Arrange
        int id = 1;
        Film film = new Film();
        // Populate film with invalid test data

        doThrow(IllegalArgumentException.class).when(filmService).updateFilm(id, film);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> filmController.updateFilm(id, film));
    }

    @Test
    void deleteFilm_ValidId() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<Void> responseEntity = filmController.deleteFilm(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(filmService, times(1)).deleteFilm(id);
    }

    @Test
    void deleteFilm_InvalidId() {
        // Arrange
        int id = 999; // Non-existing ID

        // Act
        ResponseEntity<Void> responseEntity = filmController.deleteFilm(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(filmService, never()).deleteFilm(id);
    }
}

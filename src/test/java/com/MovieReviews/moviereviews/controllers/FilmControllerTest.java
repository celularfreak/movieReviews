package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.controller.FilmController;
import com.MovieReviews.moviereviews.service.FilmService;
import com.MovieReviews.moviereviews.dto.FilmDTO;
import com.MovieReviews.moviereviews.model.Film;
import com.MovieReviews.moviereviews.repositories.FilmRepository;
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
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmControllerTest {

    @Mock
    private FilmService filmService;

    @InjectMocks
    private FilmController filmController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllFilms() {
        List<Film> films = new ArrayList<>();
        films.add(new Film(1L, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action"));
        films.add(new Film(2L, "Film 2", "Director 2", LocalDate.parse("2024-02-02"), "Comedy"));
        when(filmService.getAllFilms()).thenReturn(films);

        List<FilmDTO> result = filmController.getAllFilms().getBody();

        assert result != null;
        assertEquals(2, result.size());
    }

    @Test
    public void testGetFilmById() {
        Film film = new Film(1L, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action");
        when(filmService.getFilmById(1L)).thenReturn(film);

        FilmDTO result = filmController.getFilmById(1L).getBody();

        assert result != null;
        assertEquals("Film 1", result.getTitle());
        assertEquals("Director 1", result.getDirector());
    }

    @Test
    public void testAddFilm() {
        FilmDTO filmDTO = new FilmDTO(1l, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action");
        Film film = new Film(1L, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action");
        when(filmService.addFilm(any(Film.class))).thenReturn(film);

       FilmDTO result = filmController.addFilm(filmDTO).getBody();

        assert result != null;
        assertEquals("Film 1", result.getTitle());
        assertEquals("Director 1", result.getDirector());
    }

    @Test
    public void testUpdateFilm() {
        FilmDTO filmDTO = new FilmDTO(1l, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action");
        Film film = new Film(1L, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action");
        when(filmService.updateFilm(1L, film)).thenReturn(film);

        FilmDTO result = filmController.updateFilm(1L, filmDTO).getBody();

        assert result != null;
        assertEquals("Film 1", result.getTitle());
        assertEquals("Director 1", result.getDirector());
    }

    @Test
    public void testDeleteFilm() {
       doNothing().when(filmService).deleteFilm(1L);

        filmController.deleteFilm(1L);

        verify(filmService, times(1)).deleteFilm(1L);
    }
}

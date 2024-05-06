package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.dto.FilmDTO;
import com.MovieReviews.moviereviews.model.Film;
import com.MovieReviews.moviereviews.repositories.FilmRepository;
import com.MovieReviews.moviereviews.service.FilmService;
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
public class FilmControllerTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllFilms() {
        List<Film> films = new ArrayList<>();
        films.add(new Film(1L, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action"));
        films.add(new Film(2L, "Film 2", "Director 2", LocalDate.parse("2024-02-02"), "Comedy"));
        when(filmRepository.findAll()).thenReturn(films);

        List<Film> result = filmService.getAllFilms();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetFilmById() {
        Film film = new Film(1L, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action");
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        Film result = filmService.getFilmById(1L);

        assertEquals("Film 1", result.getTitle());
        assertEquals("Director 1", result.getDirector());
    }

    @Test
    public void testAddFilm() {
        FilmDTO filmDTO = new FilmDTO("Film 1", "Director 1", "2024-01-01", "Action");
        Film film = new Film(1L, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action");
        when(filmRepository.save(any(Film.class))).thenReturn(film);

        Film result = filmService.addFilm(filmDTO);

        assertEquals("Film 1", result.getTitle());
        assertEquals("Director 1", result.getDirector());
    }

    @Test
    public void testUpdateFilm() {
        FilmDTO filmDTO = new FilmDTO("Film 1", "Director 1", "2024-01-01", "Action");
        Film film = new Film(1L, "Film 1", "Director 1", LocalDate.parse("2024-01-01"), "Action");
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(filmRepository.save(any(Film.class))).thenReturn(film);

        Film result = filmService.updateFilm(1L, filmDTO);

        assertEquals("Film 1", result.getTitle());
        assertEquals("Director 1", result.getDirector());
    }

    @Test
    public void testDeleteFilm() {
        doNothing().when(filmRepository).deleteById(1L);

        filmService.deleteFilm(1L);

        verify(filmRepository, times(1)).deleteById(1L);
    }
}

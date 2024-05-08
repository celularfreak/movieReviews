package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.model.Film;
import com.MovieReviews.moviereviews.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> films = filmService.getAllFilms();
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Film film = filmService.getFilmById(id);
        if (film != null) {
            return new ResponseEntity<>(film, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Film> addFilm(@RequestBody Film film) {
        Film addedFilm = filmService.addFilm(film);
        return new ResponseEntity<>(addedFilm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film film) {
        Film updatedFilm = filmService.updateFilm(id, film);
        if (updatedFilm != null) {
            return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

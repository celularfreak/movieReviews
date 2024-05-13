package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.model.Film;
import com.MovieReviews.moviereviews.service.FilmService;
import jakarta.validation.Valid;
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

    @GetMapping("/search/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable int id) {
        Film film = filmService.getFilmById(id);
        if (film != null) {
            return new ResponseEntity<>(film, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<Film>> searchFilms(@PathVariable String title) {
        List<Film> films = filmService.searchFilms(title);
        if (films.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(films, HttpStatus.OK);
        }
    }

    @GetMapping("/search/genre/{genre}")
    public ResponseEntity<List<Film>> searchFilmsByGenre(@PathVariable String genre) {
        List<Film> films = filmService.searchFilmsByGenre(genre);
        if(films.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(films, HttpStatus.OK);
        }
    }

    @GetMapping("/search/director/{director}")
    public ResponseEntity<List<Film>> searchFilmsByDirector(@PathVariable String director) {
        List<Film> films = filmService.searchFilmsByDirector(director);
        if(films.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(films, HttpStatus.OK);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<?> addFilm(@Valid @RequestBody Film film) {
        try {
            Film addedFilm = filmService.addFilm(film);
            return new ResponseEntity<>(addedFilm, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable int id, @Valid @RequestBody Film film) {
        try {
            Film updatedFilm = filmService.updateFilm(id, film);
            if (updatedFilm != null) {
                return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable int id) {
        filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

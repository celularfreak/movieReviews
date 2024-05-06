package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.dto.FilmDTO;
import com.MovieReviews.moviereviews.model.Film;
import com.MovieReviews.moviereviews.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        List<FilmDTO> films = filmService.getAllFilms().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable Long id) {
        Film film = filmService.getFilmById(id);
        if (film != null) {
            return new ResponseEntity<>(convertToDTO(film), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FilmDTO> addFilm(@RequestBody FilmDTO filmDTO) {
        Film film = filmService.addFilm(convertToEntity(filmDTO));
        return new ResponseEntity<>(convertToDTO(film), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Long id, @RequestBody FilmDTO filmDTO) {
        Film film = filmService.updateFilm(id, convertToEntity(filmDTO));
        if (film != null) {
            return new ResponseEntity<>(convertToDTO(film), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Métodos de conversión de DTO a entidad y viceversa
    private Film convertToEntity(FilmDTO filmDTO) {
        return new Film(
                filmDTO.getTitle(),
                filmDTO.getDirector(),
                filmDTO.getLaunchDate(),
                filmDTO.getGenre()
        );
    }

    private FilmDTO convertToDTO(Film film) {
        return new FilmDTO(
                film.getId(),
                film.getTitle(),
                film.getDirector(),
                film.getLaunchDate(),
                film.getGenre()
        );
    }
}


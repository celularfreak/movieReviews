package com.MovieReviews.moviereviews.service;

import com.MovieReviews.moviereviews.model.Film;
import com.MovieReviews.moviereviews.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    public Film addFilm(Film film) {
        validateFilm(film);
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film film) {
        validateFilm(film);
        Film existingFilm = filmRepository.findById(id).orElse(null);
        if (existingFilm != null) {
            existingFilm.setTitle(film.getTitle());
            existingFilm.setDirector(film.getDirector());
            existingFilm.setLaunchDate(film.getLaunchDate());
            existingFilm.setGenre(film.getGenre());
            return filmRepository.save(existingFilm);
        }
        return null;
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    private void validateFilm(Film film) {
        if (filmRepository.findByTitle(film.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una película con ese título.");
        }
    }
}

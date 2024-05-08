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
        if (film.getTitle().length() > 100) {
            throw new IllegalArgumentException("El título no puede tener más de 100 caracteres.");
        }
        if (film.getDirector().length() > 50) {
            throw new IllegalArgumentException("El director no puede tener más de 50 caracteres.");
        }
        if (!film.getGenre().matches("^[a-zA-Z]+(,[a-zA-Z]+)*$")) {
            throw new IllegalArgumentException("El género debe ser una palabra o varias separadas por comas.");
        }
        // Validación de la fecha en formato dd-mm-yyyy
        if (!film.getLaunchDate().toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("La fecha de lanzamiento debe estar en formato yyyy-mm-dd.");
        }
    }
}

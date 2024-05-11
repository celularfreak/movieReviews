package com.MovieReviews.moviereviews.service.reviews;

import com.MovieReviews.moviereviews.model.reviews.FilmReview;
import com.MovieReviews.moviereviews.repositories.FilmRepository;
import com.MovieReviews.moviereviews.repositories.reviews.FilmReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmReviewService {

    private final FilmReviewRepository filmReviewRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public FilmReviewService(FilmReviewRepository filmReviewRepository, FilmRepository filmRepository) {
        this.filmReviewRepository = filmReviewRepository;
        this.filmRepository = filmRepository;
    }

    public List<FilmReview> getAllFilmReviews() {
        return filmReviewRepository.findAll();
    }

    public FilmReview getFilmReviewById(int id) {
        return filmReviewRepository.findById(id).orElse(null);
    }

    public FilmReview addFilmReview(FilmReview filmReview) {
        validateFilmReview(filmReview);
        return filmReviewRepository.save(filmReview);
    }

    public FilmReview updateFilmReview(int id, FilmReview filmReview) {
        validateFilmReview(filmReview);
        FilmReview existingFilmReview = filmReviewRepository.findById(id).orElse(null);
        if (existingFilmReview != null) {
            existingFilmReview.setRating(filmReview.getRating());
            existingFilmReview.setComment(filmReview.getComment());
            existingFilmReview.setReviewDate(filmReview.getReviewDate());
            return filmReviewRepository.save(existingFilmReview);
        }
        return null;
    }

    public void deleteFilmReview(int id) {
        filmReviewRepository.deleteById(id);
    }


    private void validateFilmReview(FilmReview filmReview) {
        if (!filmRepository.existsById(filmReview.getFilmId())) {
            throw new IllegalArgumentException("La película no existe.");
        }
        if (filmReviewRepository.findByUsernameAndFilmId(filmReview.getUsername(), filmReview.getFilmId()).isPresent()) {
            throw new IllegalArgumentException("Ya has añadido una reseña para esta película.");
        }
    }
}

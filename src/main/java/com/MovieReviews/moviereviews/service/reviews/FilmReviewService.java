package com.MovieReviews.moviereviews.service.reviews;

import com.MovieReviews.moviereviews.model.reviews.FilmReview;
import com.MovieReviews.moviereviews.repositories.reviews.FilmReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmReviewService {

    private final FilmReviewRepository filmReviewRepository;

    @Autowired
    public FilmReviewService(FilmReviewRepository filmReviewRepository) {
        this.filmReviewRepository = filmReviewRepository;
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
        // Puedes agregar validaciones específicas para las revisiones de películas si es necesario
    }
}

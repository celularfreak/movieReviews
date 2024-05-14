package com.MovieReviews.moviereviews.service.reviews;

import com.MovieReviews.moviereviews.model.reviews.TvSeriesReview;
import com.MovieReviews.moviereviews.repositories.TvSeriesRepository;
import com.MovieReviews.moviereviews.repositories.reviews.TvSeriesReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvSeriesReviewService {

    private final TvSeriesReviewRepository tvSeriesReviewRepository;
    private final TvSeriesRepository tvSeriesRepository;

    @Autowired
    public TvSeriesReviewService(TvSeriesReviewRepository tvSeriesReviewRepository, TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesReviewRepository = tvSeriesReviewRepository;
        this.tvSeriesRepository = tvSeriesRepository;
    }

    public List<TvSeriesReview> getAllTvSeriesReviews() {
        return tvSeriesReviewRepository.findAll();
    }

    public TvSeriesReview getTvSeriesReviewById(int id) {
        return tvSeriesReviewRepository.findById(id).orElse(null);
    }

    public TvSeriesReview addTvSeriesReview(TvSeriesReview tvSeriesReview) {
        validateTvSeriesReview(tvSeriesReview);
        return tvSeriesReviewRepository.save(tvSeriesReview);
    }

    public TvSeriesReview updateTvSeriesReview(int id, TvSeriesReview tvSeriesReview) {
        validateTvSeriesReviewUpdate(tvSeriesReview);
        TvSeriesReview existingTvSeriesReview = tvSeriesReviewRepository.findById(id).orElse(null);
        if (existingTvSeriesReview != null) {
            existingTvSeriesReview.setRating(tvSeriesReview.getRating());
            existingTvSeriesReview.setComment(tvSeriesReview.getComment());
            existingTvSeriesReview.setReviewDate(tvSeriesReview.getReviewDate());
            return tvSeriesReviewRepository.save(existingTvSeriesReview);
        }
        return null;
    }

    public void deleteTvSeriesReview(int id) {
        tvSeriesReviewRepository.deleteById(id);
    }

    private void validateTvSeriesReview(TvSeriesReview tvSeriesReview) {
        if(!tvSeriesRepository.existsById(tvSeriesReview.getTvSeriesId())) {
            throw new IllegalArgumentException("La serie no existe.");
        }
        if (tvSeriesReviewRepository.findByUsernameAndTvSeriesId(tvSeriesReview.getUsername(), tvSeriesReview.getTvSeriesId()).isPresent()) {
            throw new IllegalArgumentException("Ya has hecho una reseña de esta serie.");
        }
    }

    private void validateTvSeriesReviewUpdate(TvSeriesReview tvSeriesReview) {
        if(!tvSeriesRepository.existsById(tvSeriesReview.getTvSeriesId())) {
            throw new IllegalArgumentException("La serie no existe.");
        }
    }

    public List<TvSeriesReview> getTvSeriesReviewByUsername(String username) {
        return tvSeriesReviewRepository.findByUsername(username);
    }
}

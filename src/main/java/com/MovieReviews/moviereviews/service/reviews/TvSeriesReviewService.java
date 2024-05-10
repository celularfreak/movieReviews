package com.MovieReviews.moviereviews.service.reviews;

import com.MovieReviews.moviereviews.model.reviews.TvSeriesReview;
import com.MovieReviews.moviereviews.repositories.reviews.TvSeriesReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvSeriesReviewService {

    private final TvSeriesReviewRepository tvSeriesReviewRepository;

    @Autowired
    public TvSeriesReviewService(TvSeriesReviewRepository tvSeriesReviewRepository) {
        this.tvSeriesReviewRepository = tvSeriesReviewRepository;
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
        validateTvSeriesReview(tvSeriesReview);
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

        if (tvSeriesReviewRepository.findByUsernameAndTvSeriesId(tvSeriesReview.getUsername(), tvSeriesReview.getTvSeriesId()).isPresent()) {
            throw new IllegalArgumentException("You have already added a review for this tv series.");
        }
    }
}

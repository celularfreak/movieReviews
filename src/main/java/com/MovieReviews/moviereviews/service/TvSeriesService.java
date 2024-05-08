package com.MovieReviews.moviereviews.service;

import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.repositories.TvSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvSeriesService {

    private final TvSeriesRepository tvSeriesRepository;

    @Autowired
    public TvSeriesService(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    public List<TvSeries> getAllTvSeries() {
        return tvSeriesRepository.findAll();
    }

    public TvSeries getTvSeriesById(Long id) {
        return tvSeriesRepository.findById(id).orElse(null);
    }

    public TvSeries addTvSeries(TvSeries tvSeries) {
        validateTvSeries(tvSeries);
        return tvSeriesRepository.save(tvSeries);
    }

    public TvSeries updateTvSeries(Long id, TvSeries tvSeries) {
        validateTvSeries(tvSeries);
        TvSeries existingTvSeries = tvSeriesRepository.findById(id).orElse(null);
        if (existingTvSeries != null) {
            existingTvSeries.setTitle(tvSeries.getTitle());
            existingTvSeries.setLaunchDate(tvSeries.getLaunchDate());
            existingTvSeries.setGenre(tvSeries.getGenre());
            existingTvSeries.setNumberSeasons(tvSeries.getNumberSeasons());
            existingTvSeries.setNumberEpisodes(tvSeries.getNumberEpisodes());
            existingTvSeries.setFinishDate(tvSeries.getFinishDate());
            return tvSeriesRepository.save(existingTvSeries);
        }
        return null;
    }

    public void deleteTvSeries(Long id) {
        tvSeriesRepository.deleteById(id);
    }

    private void validateTvSeries(TvSeries tvSeries) {
        if (tvSeries.getTitle().length() > 100) {
            throw new IllegalArgumentException("El título no puede tener más de 100 caracteres.");
        }
        if (!tvSeries.getGenre().matches("^[a-zA-Z]+(,[a-zA-Z]+)*$")) {
            throw new IllegalArgumentException("El género debe ser una palabra o varias separadas por comas.");
        }
        if (tvSeries.getNumberSeasons() < 1) {
            throw new IllegalArgumentException("El número de temporadas debe ser como mínimo 1.");
        }
        if (tvSeries.getNumberEpisodes() <= 1) {
            throw new IllegalArgumentException("El número de episodios debe ser como mínimo 1.");
        }
        if (tvSeries.getFinishDate() != null && tvSeries.getFinishDate().isBefore(tvSeries.getLaunchDate())) {
            throw new IllegalArgumentException("La fecha de finalización debe ser posterior a la fecha de lanzamiento.");
        }
    }
}

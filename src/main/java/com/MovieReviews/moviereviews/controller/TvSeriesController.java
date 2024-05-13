package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.service.TvSeriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tv-series")
public class TvSeriesController {

    private final TvSeriesService tvSeriesService;

    @Autowired
    public TvSeriesController(TvSeriesService tvSeriesService) {
        this.tvSeriesService = tvSeriesService;
    }

    @GetMapping
    public ResponseEntity<List<TvSeries>> getAllTvSeries() {
        List<TvSeries> tvSeriesList = tvSeriesService.getAllTvSeries();
        return new ResponseEntity<>(tvSeriesList, HttpStatus.OK);
    }

    @GetMapping("search/{id}")
    public ResponseEntity<TvSeries> getTvSeriesById(@PathVariable int id) {
        TvSeries tvSeries = tvSeriesService.getTvSeriesById(id);
        if (tvSeries != null) {
            return new ResponseEntity<>(tvSeries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<TvSeries>> searchTvSeries(@PathVariable String title) {
        List<TvSeries> tvSeriesList = tvSeriesService.searchTvSeries(title);
        if (tvSeriesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tvSeriesList, HttpStatus.OK);
        }
    }

    @GetMapping("/search/genre/{genre}")
public ResponseEntity<List<TvSeries>> searchTvSeriesByGenre(@PathVariable String genre) {
        List<TvSeries> tvSeriesList = tvSeriesService.searchTvSeriesByGenre(genre);
        if(tvSeriesList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tvSeriesList, HttpStatus.OK);
        }
    }

    @GetMapping("/search/seasons/{seasons}")
public ResponseEntity<List<TvSeries>> searchTvSeriesBySeasons(@PathVariable int seasons) {
        List<TvSeries> tvSeriesList = tvSeriesService.searchTvSeriesBySeasons(seasons);
        if(tvSeriesList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tvSeriesList, HttpStatus.OK);
        }
    }

    @GetMapping("/search/episodes/{episodes}")
public ResponseEntity<List<TvSeries>> searchTvSeriesByEpisodes(@PathVariable int episodes) {
        List<TvSeries> tvSeriesList = tvSeriesService.searchTvSeriesByEpisodes(episodes);
        if(tvSeriesList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tvSeriesList, HttpStatus.OK);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<?> addTvSeries(@Valid @RequestBody TvSeries tvSeries) {
        try {
            TvSeries addedTvSeries = tvSeriesService.addTvSeries(tvSeries);
            return new ResponseEntity<>(addedTvSeries, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTvSeries(@PathVariable int id, @Valid @RequestBody TvSeries tvSeries) {
        try {
            TvSeries updatedTvSeries = tvSeriesService.updateTvSeries(id, tvSeries);
            if (updatedTvSeries != null) {
                return new ResponseEntity<>(updatedTvSeries, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTvSeries(@PathVariable int id) {
        tvSeriesService.deleteTvSeries(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/average-seasons")
    public ResponseEntity<Integer> getAverageSeasons() {
        Integer averageSeasons = tvSeriesService.calculateAverageSeasons();
        if (averageSeasons != null) {
            return new ResponseEntity<>(averageSeasons, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/average-episodes")
    public ResponseEntity<Integer> getAverageEpisodes() {
        Integer averageEpisodes = tvSeriesService.calculateAverageEpisodes();
        if (averageEpisodes != null) {
            return new ResponseEntity<>(averageEpisodes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/average-episodes-per-season")
    public ResponseEntity<Integer> getAverageEpisodesPerSeason() {
        Integer averageEpisodesPerSeason = tvSeriesService.calculateAverageEpisodesPerSeason();
        if (averageEpisodesPerSeason != null) {
            return new ResponseEntity<>(averageEpisodesPerSeason, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

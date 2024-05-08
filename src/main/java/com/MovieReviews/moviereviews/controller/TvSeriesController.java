package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.service.TvSeriesService;
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

    @GetMapping("/{id}")
    public ResponseEntity<TvSeries> getTvSeriesById(@PathVariable Long id) {
        TvSeries tvSeries = tvSeriesService.getTvSeriesById(id);
        if (tvSeries != null) {
            return new ResponseEntity<>(tvSeries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TvSeries> addTvSeries(@RequestBody TvSeries tvSeries) {
        TvSeries addedTvSeries = tvSeriesService.addTvSeries(tvSeries);
        return new ResponseEntity<>(addedTvSeries, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TvSeries> updateTvSeries(@PathVariable Long id, @RequestBody TvSeries tvSeries) {
        TvSeries updatedTvSeries = tvSeriesService.updateTvSeries(id, tvSeries);
        if (updatedTvSeries != null) {
            return new ResponseEntity<>(updatedTvSeries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTvSeries(@PathVariable Long id) {
        tvSeriesService.deleteTvSeries(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

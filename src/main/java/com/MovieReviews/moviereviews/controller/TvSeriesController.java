package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.dto.TvSeriesDTO;
import com.MovieReviews.moviereviews.model.TvSeries;
import com.MovieReviews.moviereviews.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tv-series")
public class TvSeriesController {

    private final TvSeriesService tvSeriesService;

    @Autowired
    public TvSeriesController(TvSeriesService tvSeriesService) {
        this.tvSeriesService = tvSeriesService;
    }

    @GetMapping
    public ResponseEntity<List<TvSeriesDTO>> getAllTvSeries() {
        List<TvSeriesDTO> tvSeriesList = tvSeriesService.getAllTvSeries().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(tvSeriesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvSeriesDTO> getTvSeriesById(@PathVariable Long id) {
        TvSeries tvSeries = tvSeriesService.getTvSeriesById(id);
        if (tvSeries != null) {
            return new ResponseEntity<>(convertToDTO(tvSeries), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TvSeriesDTO> addTvSeries(@RequestBody TvSeriesDTO tvSeriesDTO) {
        TvSeries tvSeries = tvSeriesService.addTvSeries(convertToEntity(tvSeriesDTO));
        return new ResponseEntity<>(convertToDTO(tvSeries), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TvSeriesDTO> updateTvSeries(@PathVariable Long id, @RequestBody TvSeriesDTO tvSeriesDTO) {
        TvSeries tvSeries = tvSeriesService.updateTvSeries(id, convertToEntity(tvSeriesDTO));
        if (tvSeries != null) {
            return new ResponseEntity<>(convertToDTO(tvSeries), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTvSeries(@PathVariable Long id) {
        tvSeriesService.deleteTvSeries(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Métodos de conversión de DTO a entidad y viceversa
    private TvSeries convertToEntity(TvSeriesDTO tvSeriesDTO) {
        return new TvSeries(
                tvSeriesDTO.getTitle(),
                tvSeriesDTO.getLaunchDate(),
                tvSeriesDTO.getGenre(),
                tvSeriesDTO.getNumberSeasons(),
                tvSeriesDTO.getNumberEpisodes(),
                tvSeriesDTO.getFinishDate()
        );
    }

    private TvSeriesDTO convertToDTO(TvSeries tvSeries) {
        return new TvSeriesDTO(
                tvSeries.getId(),
                tvSeries.getTitle(),
                tvSeries.getLaunchDate(),
                tvSeries.getGenre(),
                tvSeries.getNumberSeasons(),
                tvSeries.getNumberEpisodes(),
                tvSeries.getFinishDate()
        );
    }
}

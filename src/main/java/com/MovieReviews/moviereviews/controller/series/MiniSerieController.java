package com.MovieReviews.moviereviews.controller.series;

import com.MovieReviews.moviereviews.model.series.MiniSerie;
import com.MovieReviews.moviereviews.service.series.MiniSerieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/tv-series/miniseries")
public class MiniSerieController {

    private final MiniSerieService miniSerieService;

    @Autowired
    public MiniSerieController(MiniSerieService miniSerieService) {
        this.miniSerieService = miniSerieService;
    }

    @GetMapping
    public ResponseEntity<List<MiniSerie>> getAllMiniSeries() {
        List<MiniSerie> miniSeries = miniSerieService.getAllMiniSeries();
        return new ResponseEntity<>(miniSeries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiniSerie> getMiniSerieById(@PathVariable int id) {
        MiniSerie miniSerie = miniSerieService.getMiniSerieById(id);
        if (miniSerie != null) {
            return new ResponseEntity<>(miniSerie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMiniSerie(@Valid @RequestBody MiniSerie miniSerie) {
        try {
            MiniSerie addedMiniSerie = miniSerieService.addMiniSerie(miniSerie);
            return new ResponseEntity<>(addedMiniSerie, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMiniSerie(@PathVariable int id, @Valid @RequestBody MiniSerie miniSerie) {
        try {
            MiniSerie updatedMiniSerie = miniSerieService.updateMiniSerie(id, miniSerie);
            if (updatedMiniSerie != null) {
                return new ResponseEntity<>(updatedMiniSerie, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMiniSerie(@PathVariable int id) {
        miniSerieService.deleteMiniSerie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

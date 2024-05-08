package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.model.Series.MiniSerie;
import com.MovieReviews.moviereviews.service.MiniSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/miniseries")
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
    public ResponseEntity<MiniSerie> getMiniSerieById(@PathVariable Long id) {
        MiniSerie miniSerie = miniSerieService.getMiniSerieById(id);
        if (miniSerie != null) {
            return new ResponseEntity<>(miniSerie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MiniSerie> addMiniSerie(@RequestBody MiniSerie miniSerie) {
        MiniSerie addedMiniSerie = miniSerieService.addMiniSerie(miniSerie);
        return new ResponseEntity<>(addedMiniSerie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiniSerie> updateMiniSerie(@PathVariable Long id, @RequestBody MiniSerie miniSerie) {
        MiniSerie updatedMiniSerie = miniSerieService.updateMiniSerie(id, miniSerie);
        if (updatedMiniSerie != null) {
            return new ResponseEntity<>(updatedMiniSerie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMiniSerie(@PathVariable Long id) {
        miniSerieService.deleteMiniSerie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

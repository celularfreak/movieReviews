package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.model.Series.Anime;
import com.MovieReviews.moviereviews.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public ResponseEntity<List<Anime>> getAllAnimes() {
        List<Anime> animes = animeService.getAllAnimes();
        return new ResponseEntity<>(animes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        Anime anime = animeService.getAnimeById(id);
        if (anime != null) {
            return new ResponseEntity<>(anime, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Anime> addAnime(@RequestBody Anime anime) {
        Anime addedAnime = animeService.addAnime(anime);
        return new ResponseEntity<>(addedAnime, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anime> updateAnime(@PathVariable Long id, @RequestBody Anime anime) {
        Anime updatedAnime = animeService.updateAnime(id, anime);
        if (updatedAnime != null) {
            return new ResponseEntity<>(updatedAnime, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        animeService.deleteAnime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

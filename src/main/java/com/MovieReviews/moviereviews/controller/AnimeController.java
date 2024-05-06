package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.dto.AnimeDTO;
import com.MovieReviews.moviereviews.model.Series.Anime;
import com.MovieReviews.moviereviews.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public ResponseEntity<List<AnimeDTO>> getAllAnimes() {
        List<AnimeDTO> animes = animeService.getAllAnimes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(animes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeDTO> getAnimeById(@PathVariable Long id) {
        Anime anime = animeService.getAnimeById(id);
        if (anime != null) {
            return new ResponseEntity<>(convertToDTO(anime), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AnimeDTO> addAnime(@RequestBody AnimeDTO animeDTO) {
        Anime anime = animeService.addAnime(convertToEntity(animeDTO));
        return new ResponseEntity<>(convertToDTO(anime), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeDTO> updateAnime(@PathVariable Long id, @RequestBody AnimeDTO animeDTO) {
        Anime anime = animeService.updateAnime(id, convertToEntity(animeDTO));
        if (anime != null) {
            return new ResponseEntity<>(convertToDTO(anime), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        animeService.deleteAnime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Métodos de conversión de DTO a entidad y viceversa
    private Anime convertToEntity(AnimeDTO animeDTO) {
        return new Anime(
                animeDTO.getTitle(),
                animeDTO.getLaunchDate(),
                animeDTO.getGenre(),
                animeDTO.getNumberSeasons(),
                animeDTO.getNumberEpisodes(),
                animeDTO.getFinishDate(),
                animeDTO.getAnimationStudio()
        );
    }

    private AnimeDTO convertToDTO(Anime anime) {
        return new AnimeDTO(
                anime.getId(),
                anime.getTitle(),
                anime.getLaunchDate(),
                anime.getGenre(),
                anime.getNumberSeasons(),
                anime.getNumberEpisodes(),
                anime.getFinishDate(),
                anime.getAnimationStudio()
        );
    }
}

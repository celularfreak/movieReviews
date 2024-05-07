package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.dto.MiniSerieDTO;
import com.MovieReviews.moviereviews.model.Series.MiniSerie;
import com.MovieReviews.moviereviews.service.MiniSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/miniseries")
public class MiniSerieController {

    private final MiniSerieService miniSerieService;

    @Autowired
    public MiniSerieController(MiniSerieService miniSerieService) {
        this.miniSerieService = miniSerieService;
    }

    @GetMapping
    public ResponseEntity<List<MiniSerieDTO>> getAllMiniSeries() {
        List<MiniSerieDTO> miniSeries = miniSerieService.getAllMiniSeries().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(miniSeries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiniSerieDTO> getMiniSerieById(@PathVariable Long id) {
        MiniSerie miniSerie = miniSerieService.getMiniSerieById(id);
        if (miniSerie != null) {
            return new ResponseEntity<>(convertToDTO(miniSerie), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MiniSerieDTO> addMiniSerie(@RequestBody MiniSerieDTO miniSerieDTO) {
        MiniSerie miniSerie = miniSerieService.addMiniSerie(convertToEntity(miniSerieDTO));
        return new ResponseEntity<>(convertToDTO(miniSerie), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiniSerieDTO> updateMiniSerie(@PathVariable Long id, @RequestBody MiniSerieDTO miniSerieDTO) {
        MiniSerie miniSerie = miniSerieService.updateMiniSerie(id, convertToEntity(miniSerieDTO));
        if (miniSerie != null) {
            return new ResponseEntity<>(convertToDTO(miniSerie), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMiniSerie(@PathVariable Long id) {
        miniSerieService.deleteMiniSerie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Métodos de conversión de DTO a entidad y viceversa
    private MiniSerie convertToEntity(MiniSerieDTO miniSerieDTO) {
        return new MiniSerie(
                miniSerieDTO.getId(),
                miniSerieDTO.getTitle(),
                miniSerieDTO.getLaunchDate(),
                miniSerieDTO.getGenre(),
                miniSerieDTO.getNumberEpisodes(),
                miniSerieDTO.getFinishDate()
        );
    }

    private MiniSerieDTO convertToDTO(MiniSerie miniSerie) {
        return new MiniSerieDTO(
                miniSerie.getId(),
                miniSerie.getTitle(),
                miniSerie.getLaunchDate(),
                miniSerie.getGenre(),
                miniSerie.getNumberEpisodes(),
                miniSerie.getFinishDate()
        );
    }
}

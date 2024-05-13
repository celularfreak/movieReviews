package com.MovieReviews.moviereviews.service.series;

import com.MovieReviews.moviereviews.model.series.MiniSerie;
import com.MovieReviews.moviereviews.repositories.series.MiniSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiniSerieService {

    private final MiniSerieRepository miniSerieRepository;

    @Autowired
    public MiniSerieService(MiniSerieRepository miniSerieRepository) {
        this.miniSerieRepository = miniSerieRepository;
    }

    public List<MiniSerie> getAllMiniSeries() {
        return miniSerieRepository.findAll();
    }

    public MiniSerie getMiniSerieById(int id) {
        return miniSerieRepository.findById(id).orElse(null);
    }

    public MiniSerie addMiniSerie(MiniSerie miniSerie) {
        validateMiniSerie(miniSerie);
        return miniSerieRepository.save(miniSerie);
    }

    public MiniSerie updateMiniSerie(int id, MiniSerie miniSerie) {
        validateMiniSerieUpdate(miniSerie);
        MiniSerie existingMiniSerie = miniSerieRepository.findById(id).orElse(null);
        if (existingMiniSerie != null) {
            existingMiniSerie.setTitle(miniSerie.getTitle());
            existingMiniSerie.setLaunchDate(miniSerie.getLaunchDate());
            existingMiniSerie.setGenre(miniSerie.getGenre());
            existingMiniSerie.setNumberEpisodes(miniSerie.getNumberEpisodes());
            existingMiniSerie.setFinishDate(miniSerie.getFinishDate());
            return miniSerieRepository.save(existingMiniSerie);
        }
        return null;
    }

    public void deleteMiniSerie(int id) {
        miniSerieRepository.deleteById(id);
    }

    private void validateMiniSerie(MiniSerie miniSerie) {
        if (miniSerieRepository.findByTitle(miniSerie.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una miniserie con ese título.");
        }
        if (miniSerie.getFinishDate() == null) {
            throw new IllegalArgumentException("La miniserie debe tener una fecha de finalización.");
        }
    }

    private void validateMiniSerieUpdate(MiniSerie miniSerie) {
        if (miniSerie.getFinishDate() == null) {
            throw new IllegalArgumentException("La miniserie debe tener una fecha de finalización.");
        }
    }
}

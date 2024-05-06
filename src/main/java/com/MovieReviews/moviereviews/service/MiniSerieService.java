package com.MovieReviews.moviereviews.service;

import com.MovieReviews.moviereviews.model.Series.MiniSerie;
import com.MovieReviews.moviereviews.repositories.MiniSerieRepository;
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

    public MiniSerie getMiniSerieById(Long id) {
        return miniSerieRepository.findById(id).orElse(null);
    }

    public MiniSerie addMiniSerie(MiniSerie miniSerie) {
        return miniSerieRepository.save(miniSerie);
    }

    public MiniSerie updateMiniSerie(Long id, MiniSerie miniSerie) {
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

    public void deleteMiniSerie(Long id) {
        miniSerieRepository.deleteById(id);
    }
}

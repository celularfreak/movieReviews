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
        validateMiniSerie(miniSerie);
        return miniSerieRepository.save(miniSerie);
    }

    public MiniSerie updateMiniSerie(Long id, MiniSerie miniSerie) {
        validateMiniSerie(miniSerie);
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

    private void validateMiniSerie(MiniSerie miniSerie) {
        if (miniSerie.getTitle().length() > 100) {
            throw new IllegalArgumentException("El título no puede tener más de 100 caracteres.");
        }
        if (!miniSerie.getGenre().matches("^[a-zA-Z]+(,[a-zA-Z]+)*$")) {
            throw new IllegalArgumentException("El género debe ser una palabra o varias separadas por comas.");
        }
        if (miniSerie.getNumberEpisodes() <= 1) {
            throw new IllegalArgumentException("El número de episodios debe ser mayor a 1.");
        }
        if (miniSerie.getFinishDate() != null && miniSerie.getFinishDate().isBefore(miniSerie.getLaunchDate())) {
            throw new IllegalArgumentException("La fecha de finalización debe ser posterior a la fecha de lanzamiento.");
        }
    }
}

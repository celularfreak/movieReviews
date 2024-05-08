package com.MovieReviews.moviereviews.service;

import com.MovieReviews.moviereviews.model.Series.Anime;
import com.MovieReviews.moviereviews.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAllAnimes() {
        return animeRepository.findAll();
    }

    public Anime getAnimeById(Long id) {
        return animeRepository.findById(id).orElse(null);
    }

    public Anime addAnime(Anime anime) {
        validateAnime(anime);
        return animeRepository.save(anime);
    }

    public Anime updateAnime(Long id, Anime anime) {
        validateAnime(anime);
        Anime existingAnime = animeRepository.findById(id).orElse(null);
        if (existingAnime != null) {
            existingAnime.setTitle(anime.getTitle());
            existingAnime.setLaunchDate(anime.getLaunchDate());
            existingAnime.setGenre(anime.getGenre());
            existingAnime.setNumberSeasons(anime.getNumberSeasons());
            existingAnime.setNumberEpisodes(anime.getNumberEpisodes());
            existingAnime.setFinishDate(anime.getFinishDate());
            existingAnime.setAnimationStudio(anime.getAnimationStudio());
            return animeRepository.save(existingAnime);
        }
        return null;
    }

    public void deleteAnime(Long id) {
        animeRepository.deleteById(id);
    }

    private void validateAnime(Anime anime) {
        if (anime.getTitle().length() > 100) {
            throw new IllegalArgumentException("El título no puede tener más de 100 caracteres.");
        }
        if (!anime.getLaunchDate().toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("La fecha de lanzamiento debe estar en formato yyyy-mm-dd.");
        }
        if (!anime.getFinishDate().toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("La fecha de finalización debe estar en formato yyyy-mm-dd.");
        }
        if (!anime.getGenre().matches("^[a-zA-Z]+(,[a-zA-Z]+)*$")) {
            throw new IllegalArgumentException("El género debe ser una palabra o varias separadas por comas.");
        }
        if (anime.getNumberSeasons() <= 1) {
            throw new IllegalArgumentException("El número de temporadas debe ser igual o mayor que 1.");
        }
        if (anime.getNumberEpisodes() <= 1) {
            throw new IllegalArgumentException("El número de episodios debe ser igual o mayor que 1.");
        }
        if (anime.getAnimationStudio().length() > 50) {
            throw new IllegalArgumentException("El nombre del estudio de animación no puede tener más de 50 caracteres.");
        }
        if (anime.getFinishDate() != null && anime.getFinishDate().isBefore(anime.getLaunchDate())) {
            throw new IllegalArgumentException("La fecha de finalización debe ser posterior a la fecha de lanzamiento.");
        }
    }
}

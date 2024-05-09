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
        if (animeRepository.findByTitle(anime.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un anime con ese título.");
        }
    }
}

package code.service;

import code.model.Category;
import code.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISongService {
    Page<Song> findAllSongs(Pageable pageable);

    Song save(Song song);

    void deleteById(Long id);

    Optional<Song> findById(Long id);

    Page<Song> findAllByNameContaining(Pageable pageable, String name);

    Iterable<Song> findAllByCategory(Category category);
}

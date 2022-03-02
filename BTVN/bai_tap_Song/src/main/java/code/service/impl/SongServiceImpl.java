package code.service.impl;

import code.model.Category;
import code.model.Song;
import code.repository.ISongRepository;
import code.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    private ISongRepository iSongRepository;

    @Override
    public Page<Song> findAllSongs(Pageable pageable) {
        return iSongRepository.findAll(pageable);
    }

    @Override
    public Song save(Song song) {
        return iSongRepository.save(song);
    }

    @Override
    public void deleteById(Long id) {
        iSongRepository.deleteById(id);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return iSongRepository.findById(id);
    }

    @Override
    public Page<Song> findAllByNameContaining(Pageable pageable, String name) {
        return iSongRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Iterable<Song> findAllByCategory(Category category) {
        return iSongRepository.findAllByCategory(category);
    }
}

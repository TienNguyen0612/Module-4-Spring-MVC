package code.repository;

import code.model.Category;
import code.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepository extends PagingAndSortingRepository<Song, Long> {
    Page<Song> findAllByNameContaining(Pageable pageable, String name);

    Iterable<Song> findAllByCategory(Category category);
}

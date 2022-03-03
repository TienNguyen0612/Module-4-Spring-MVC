package codegym.repository;

import codegym.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IProvinceRepository extends PagingAndSortingRepository<Province, Long> {
}

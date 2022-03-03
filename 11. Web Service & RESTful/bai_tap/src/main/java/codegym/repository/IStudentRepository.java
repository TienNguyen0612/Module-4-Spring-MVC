package codegym.repository;

import codegym.model.Province;
import codegym.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IStudentRepository extends PagingAndSortingRepository<Student, Long> {
    Iterable<Student> findAllByNameContaining(String name);

    void deleteAllByProvinceId(Long id);
}

package codegym.service;

import codegym.model.Student;

public interface IStudentService extends IGeneralService<Student> {
    Iterable<Student> findAllByNameContaining(String name);

    void deleteAllByProvinceId(Long id);
}

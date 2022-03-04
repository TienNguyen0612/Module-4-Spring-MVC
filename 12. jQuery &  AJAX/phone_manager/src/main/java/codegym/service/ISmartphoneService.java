package codegym.service;

import codegym.model.Smartphone;

import java.util.Optional;

public interface ISmartphoneService {
    Iterable<Smartphone> findAll();

    Optional<Smartphone> findById(Long id);

    Smartphone save(Smartphone smartphone);

    void remove(Long id);
}

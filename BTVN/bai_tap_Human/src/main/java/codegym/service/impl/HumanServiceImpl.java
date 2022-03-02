package codegym.service.impl;

import codegym.model.Human;
import codegym.repository.IHumanRepository;
import codegym.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HumanServiceImpl implements IHumanService {
    @Autowired
    private IHumanRepository iHumanRepository;

    @Override
    public Page<Human> findAll(Pageable pageable) {
        return iHumanRepository.findAll(pageable);
    }

    @Override
    public Human save(Human human) {
        iHumanRepository.save(human);
        return human;
    }

    @Override
    public void delete(Long id) {
        iHumanRepository.deleteById(id);
    }

    @Override
    public Optional<Human> findById(Long id) {
        return iHumanRepository.findById(id);
    }

    @Override
    public Page<Human> findByNameContaining(Pageable pageable, String name) {
        return iHumanRepository.findAllByNameContaining(pageable, name);
    }
}

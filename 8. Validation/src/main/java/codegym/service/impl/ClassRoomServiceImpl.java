package codegym.service.impl;

import codegym.model.ClassRoom;
import codegym.repository.IClassRoomRepository;
import codegym.service.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassRoomServiceImpl implements IClassRoomService {

    @Autowired
    IClassRoomRepository classRoomRepository;

    @Override
    public Iterable<ClassRoom> findClassAll() {
        return classRoomRepository.findAll();
    }

    @Override
    public Optional<ClassRoom> findClassRoomById(long id) {
        return classRoomRepository.findById(id);
    }
}

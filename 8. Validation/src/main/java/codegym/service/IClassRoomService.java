package codegym.service;

import codegym.model.ClassRoom;
import java.util.Optional;

public interface IClassRoomService {
    Iterable<ClassRoom> findClassAll();

    Optional<ClassRoom> findClassRoomById(long id);
}

package main.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByShortDescriptionIgnoreCaseContainingOrFullDescriptionIgnoreCaseContaining(String name, String description);

    List<Task> findByAccomplishedTrue();

    List<Task> findByAccomplishedFalse();
}

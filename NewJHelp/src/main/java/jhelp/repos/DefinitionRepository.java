package jhelp.repos;

import jhelp.orm.Term;
import org.springframework.data.repository.CrudRepository;
import jhelp.orm.Definition;

import java.util.List;
import java.util.Optional;


public interface DefinitionRepository extends CrudRepository<Definition, Integer> {
    Optional<Definition> findDefinitionById(Integer i);
}


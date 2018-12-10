package jhelp.repos;

import org.springframework.data.repository.CrudRepository;
import jhelp.orm.Definition;


public interface DefinitionRepository extends CrudRepository<Definition, Integer> {
}

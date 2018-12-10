package jhelp.repos;

import org.springframework.data.repository.CrudRepository;
import jhelp.orm.Term;

public interface TermRepository extends CrudRepository<Term, Integer> {
}

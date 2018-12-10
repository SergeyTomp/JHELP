package jhelp.repos;

import jhelp.orm.Term;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TermRepository extends CrudRepository<Term, Integer> {
Optional<Term> findById(Integer i);
}

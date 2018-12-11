package jhelp.repos;

import jhelp.orm.Definition;
import org.springframework.data.repository.CrudRepository;
import jhelp.orm.Term;

import java.util.List;

public interface TermRepository extends CrudRepository<Term, Integer> {
    List<Term> findByDefinitions(Definition definition);
}

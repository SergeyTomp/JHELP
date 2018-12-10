package jhelp.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Term {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "term")
    private String term;

    @OneToMany(mappedBy = "term_id")
    List<Definition> definitions = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getTerm() {
        return term;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }
}

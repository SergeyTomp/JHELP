package jhelp.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "term")
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

    @Override
    public String toString() {
        return "Term{" +
                "term='" + term + '\'' +
                '}';
    }
}

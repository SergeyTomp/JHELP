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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "term", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Definition> definitions = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getTerm() {
        return term;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void addDefinition(Definition definition) {
        definitions.add(definition);
        definition.setTerm(this);
    }



    @Override
    public String toString() {
        return "Term{" +
                "term='" + term + '\'' +
                '}';
    }
}

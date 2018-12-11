package jhelp.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "definition")
public class Definition {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "definition")
    private String definition;

    @Column(name = "term_id")
    private Integer term_id;

    public Integer getId() {
        return id;
    }

    public String getDefinition() {
        return definition;
    }

    public Integer getTerm_id() {
        return term_id;
    }
}

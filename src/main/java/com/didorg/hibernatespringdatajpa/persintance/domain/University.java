package com.didorg.hibernatespringdatajpa.persintance.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String university_name;


    // One "University" to Many "Students".
    // With mappedBy, we can create a bidirectional relationship. mappedBy in the parent entity.
    /* You should avoid unidirectional OneToMany associations in your domain model.
    Otherwise, Hibernate might create unexpected tables and execute more SQL statements than you expected. */
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    public University() {
    }

    public University(String university_name) {
        this.university_name = university_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + university_name + '\'' +
                '}';
    }
}

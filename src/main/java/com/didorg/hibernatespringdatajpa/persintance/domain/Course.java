package com.didorg.hibernatespringdatajpa.persintance.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String course_name;
    private Double course_fee;

    /* With @ManyToMany, we should create a third table so that we can map both entities.
    This third table will have two FK pointing to their parent tables */
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    public Course() {
    }

    public Course(String name, Double course_fee) {

        this.course_name = name;
        this.course_fee = course_fee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Double getCourse_fee() {
        return course_fee;
    }

    public void setCourse_fee(Double course_fee) {
        this.course_fee = course_fee;
    }
}

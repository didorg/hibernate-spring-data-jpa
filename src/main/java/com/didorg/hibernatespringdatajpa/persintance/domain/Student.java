package com.didorg.hibernatespringdatajpa.persintance.domain;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "age", nullable = false)
    private Integer age;

    // Student will have the parent role. It does not make sense tuition to exist if student does not exist
    // A good practice is to use cascade in the parent entity so that we can propagate the changes and apply them into children.
    // With mappedBy, we can create a bidirectional relationship, even though we just have one FK, we can link both tables.
    // orphanRemoval=true means child entity should be removed automatically by the ORM if it's no longer referenced by a parent entity.
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Tuition tuition;

    // Many "Student" to One "University". mappedBy in the parent entity
    // The owning side of these relationships is usually in the @ManyToOne (owner of that FK)
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    /* I have decided that the owning side is student and is where we use the @JoinTable annotation,
     also to use Cascade Merge and Persist but not Cascade.Remove because if I delete a course, I don’t want to remove the students from that course. */
    /* The use of Set, it will avoid Hibernate deletes all the rows in student_course link to that entity, and re-insert the ones we did not want to delete.
     This is of course not efficient and unnecessary. */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_course", // name of the table that links both tables (student_course)
            joinColumns = {@JoinColumn(name = "student_id")}, // Points to the owning side table (student)
            inverseJoinColumns = {@JoinColumn(name = "course_id")} // Points to the inverse table of the owning side (course)
    )
    private Set<Course> courses;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, Integer age, Tuition tuition, University university, Set<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.tuition = tuition;
        this.university = university;
        this.courses = courses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}

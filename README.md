# Hibernate - Spring Data JPA  
`spring-data-jpa
`  
### Entity Relationship Diagram (ER Diagram)

![hibernate-spring-data-jpa](https://user-images.githubusercontent.com/22514083/111009397-83215c00-8361-11eb-9f1e-3d562f99c14c.png)

### @OneToOne (bidirectional)  
#### Student  
Student will have the parent role. It does not make sense tuition to exist if student does not exist.  
A good practice is to use `cascade` in the parent entity so that we can propagate the changes and apply them into children.  
With `mappedBy`, we can create a bidirectional relationship, even though we just have one FK, we can link both tables.  
`OrphanRemoval=true` means child entity should be removed automatically by the ORM if it's no longer referenced by a parent entity.  
`fetchType=LAZY`, retrieves entity, only when we really need it.  
```  
@Entity
@Table(name = "student")
public class Student {  
    ...  
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Tuition tuition;  
    ...  
```  
#### Tuition  
Tuition is the owner of the relationship, owner of that FK (owning side).   
```  
@Entity
@Table(name = "tuition")
public class Tuition {  
    ...  
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student; 
    ...
``` 

### @ManyToMany (bidirectional)  
#### Course  
With `@ManyToMany` we should create a third table so that we can map both entities. This third table will have two FK pointing to their parent tables  
```  
@Entity
@Table(name = "course")
public class Course {  
    ...  
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;  
    ...  
```  
#### Student  
I have decided that the owning side is student and is where we use the `@JoinTable` annotation, also to use **Cascade Merge and Persist** but not `Cascade.REMOVE` because if I delete a course, I don’t want to remove the students from that course.  
The use of **Set**, it will avoid **Hibernate** deletes all the rows in `student_course` link to that entity, and re-insert the ones we did not want to delete. This is of course not efficient and unnecessary.    
```  
@Entity
@Table(name = "student")
public class Student {  
    ...  
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_course", // name of the table that links both tables (student_course)
            joinColumns = {@JoinColumn(name = "student_id")}, // Points to the owning side table (student)
            inverseJoinColumns = {@JoinColumn(name = "course_id")} // Points to the inverse table of the owning side (course)
    )
    private Set<Course> courses;
    ...
``` 

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
public class Student {  
    ...  
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Tuition tuition;  
    ...  
```  
#### Tuition  
Tuition is the owner of the relationship, owner of that FK (owning side).   
```  
public class Tuition {  
    ...  
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student; 
    ...
``` 

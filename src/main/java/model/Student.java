package model;

import org.hibernate.action.internal.OrphanRemovalAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

//    @ManyToMany(fetch=FetchType.LAZY,
//            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(
//            name="course_student",
//            joinColumns=@JoinColumn(name="student_id"),
//            inverseJoinColumns=@JoinColumn(name="course_id")
//    )
//    private List<Course> courses;

    @ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="class_id")
    private Class aClass;

    public Student() {

    }

    public Student(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Class getaClass() {
        return aClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }
}

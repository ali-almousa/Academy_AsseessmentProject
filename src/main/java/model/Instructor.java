package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;


    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="instructor_course",
            joinColumns=@JoinColumn(name="instructor_id"),
            inverseJoinColumns=@JoinColumn(name="course_id")
    )
    private Set<Course> courses;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="class_instructor",
            joinColumns=@JoinColumn(name="instructor_id"),
            inverseJoinColumns=@JoinColumn(name="class_id")
    )
    private Set<Class> classes;


    public Instructor() {

    }

    public Instructor(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }

    public Set<Class> getClasses() {
        return classes;
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    // add convenience methods for bi-directional relationship
//
//    public void add(Course tempCourse) {
//
//        if (courses == null) {
//            courses = new ArrayList<>();
//        }
//
//        courses.add(tempCourse);
//
//        tempCourse.setInstructor(this);
//    }

}

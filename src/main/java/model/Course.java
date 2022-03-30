package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @ManyToMany(fetch=FetchType.EAGER,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="instructor_course",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="instructor_id")
    )
    private Set<Instructor> instructors;

//    @ManyToMany(fetch=FetchType.LAZY,
//            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(
//            name="course_student",
//            joinColumns=@JoinColumn(name="course_id"),
//            inverseJoinColumns=@JoinColumn(name="student_id")
//    )
//    private List<Student> students;

    @ManyToMany(fetch=FetchType.EAGER,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="class_course",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="class_id")
    )
    private Set<Class> classes;


    public Course() {

    }

    public Course(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }

    public Set<Class> getClasses() {
        return classes;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }
}

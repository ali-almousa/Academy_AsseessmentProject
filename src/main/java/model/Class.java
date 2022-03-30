package model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="class")
public class Class {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="start_month")
    private String startMonth;

    @Column(name="end_month")
    private String endMonth;

    @ManyToMany(fetch=FetchType.EAGER,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="class_course",
            joinColumns=@JoinColumn(name="class_id"),
            inverseJoinColumns=@JoinColumn(name="course_id")
    )
    private Set<Course> courses;


    @ManyToMany(fetch=FetchType.EAGER,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="class_instructor",
            joinColumns=@JoinColumn(name="class_id"),
            inverseJoinColumns=@JoinColumn(name="instructor_id")
    )
    private Set<Instructor> instructors;


    @OneToMany(fetch = FetchType.EAGER, mappedBy="aClass",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
//    private List<Student> students;
    private Set<Student> students;


    public Class() {
    }

    public Class(String name, String startMonth, String endMonth) {
        this.name = name;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public Class(int id, String name, String startMonth, String endMonth) {
        this.id = id;
        this.name = name;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startMonth='" + startMonth + '\'' +
                ", endMonth='" + endMonth + '\'' +
                '}';
    }
}

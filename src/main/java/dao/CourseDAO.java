package dao;

import model.Course;
import model.Class;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CourseDAO {

    public void saveCourse(Course course) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(course);
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void updateCourse(Course course) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course temp = entityManager.find(Course.class, course.getId());
            if (temp != null) {
                temp.setTitle(course.getTitle());
            }
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void deleteCourse(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course course = entityManager.find(Course.class, id);
            if (course != null) {
                entityManager.remove(course);
                System.out.println("course deleted");
            }
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public Course getCourse(int id) {
        Course course;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            course = entityManager.find(Course.class, id);
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
        return course;
    }

    @SuppressWarnings("unchecked")
    public Set<Course> getAllCourse() {
        Set<Course> courses = new HashSet<>();
//        List<Student> students;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            String hql = "from Course";
            Query query = entityManager.createQuery(hql);
            List<Course> temp = query.getResultList();
            courses.addAll(temp);

//            courses = (List<Object[]>) entityManager.createQuery("select c.id, c.title from Course c").getResultList();
//            students = (List<Student>) entityManager.createNativeQuery("select studentId, enrolmentNumber, firstName, lastName from student", "StudentMapping").getResultList();
//            Student ali = entityManager.find(Student.class, 1);
//            Student hala = new Student();
//            hala.setEnrolmentNumber(343);
//            hala.setFirstName("alosh");
//            hala.setLastName("althobity");
//            Course math = entityManager.find(Course.class, 1);
//            math.getStudents().add(hala);
//            hala.setCourse(math);
//            entityManager.persist(math);
//            entityManager.persist(hala);
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
        return courses;
    }

    public void assignCourse(int classId, int courseId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course course = entityManager.find(Course.class, courseId);
            Class aClass = entityManager.find(Class.class, classId);
            if (course != null && aClass != null) {
                course.getClasses().add(aClass);
            }
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}

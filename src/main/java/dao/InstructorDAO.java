package dao;

import model.Class;
import model.Course;
import model.Instructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class InstructorDAO {

    public void saveCourse(Instructor instructor) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(instructor);
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void updateCourse(Instructor instructor) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Instructor temp = entityManager.find(Instructor.class, instructor.getId());
            if (temp != null) {
                temp.setFirstName(instructor.getFirstName());
                temp.setLastName(instructor.getLastName());
                temp.setEmail(instructor.getEmail());
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
            Instructor instructor = entityManager.find(Instructor.class, id);
            if (instructor != null) {
                entityManager.remove(instructor);
                System.out.println("instructor deleted");
            }
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public Instructor getCourse(int id) {
        Instructor instructor;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            instructor = entityManager.find(Instructor.class, id);
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
        return instructor;
    }

    @SuppressWarnings("unchecked")
    public Set<Instructor> getAllCourse() {
        Set<Instructor> instructors = new HashSet<>();
//        List<Student> students;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            String hql = "from Instructor";
            Query query = entityManager.createQuery(hql);
            List<Instructor> temp = query.getResultList();
            instructors.addAll(temp);

//            instructors = (List<Object[]>) entityManager.createQuery("select i.id, i.firstName, i.lastName, i.email from Instructor i").getResultList();
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
        return instructors;
    }

    public void assignCourse(int classId, int courseId, int instructorId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            Class aClass = entityManager.find(Class.class, classId);
            Course course = entityManager.find(Course.class, courseId);
            if (course != null && aClass != null && instructor != null) {
                instructor.getCourses().add(course);
                instructor.getClasses().add(aClass);
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





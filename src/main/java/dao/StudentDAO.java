package dao;

import model.Class;
import model.Course;
import model.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDAO {

    public void saveStudent(Student student) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void updateStudent(Student student, int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student temp = entityManager.find(Student.class, id);
            if (temp != null) {
                temp.setLastName(student.getLastName());
                temp.setFirstName(student.getFirstName());
                temp.setEmail(student.getEmail());
//                if (student.getCourse() != null)
//                    temp.setCourse(student.getCourse());
            }
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void deleteStudent(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student student = entityManager.find(Student.class, id);
            System.out.println("student found: " + student);
            if (student != null) {
                entityManager.remove(student);
            }
            transaction.commit();
                System.out.println("student deleted");
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public Student getStudent(int id) {
        Student student;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            student = entityManager.find(Student.class, id);
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
        return student;
    }

    @SuppressWarnings("unchecked")
    public List<Student> getAllStudent() {
        List<Object[]> students;
//        List<Student> students;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            students = (List<Object[]>) entityManager.createQuery("select s.id, s.firstName, s.lastName, s.email from Student s").getResultList();
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
        List<Student> x = new ArrayList<>();
        for (Object[] obj: students) {
            Student student = new Student((Integer)obj[0], (String) obj[1],(String) obj[2], (String)obj[3]);
            x.add(student);
        }
        return x;
    }

    public void assignStudent(int classId, int studentId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student student = entityManager.find(Student.class, studentId);
            Class aClass = entityManager.find(Class.class, classId);
            if (student != null && aClass != null) {
                student.setaClass(aClass);
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

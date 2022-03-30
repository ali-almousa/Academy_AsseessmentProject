package dao;

import model.Class;
import model.Course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ClassDAO {

    public void saveCourse(Class aClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(aClass);
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void updateCourse(Class aClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Class temp = entityManager.find(Class.class, aClass.getId());
            if (temp != null) {
                temp.setName(aClass.getName());
                temp.setStartMonth(aClass.getStartMonth());
                temp.setEndMonth(aClass.getEndMonth());
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
            Class aClass = entityManager.find(Class.class, id);
            if (aClass != null) {
                entityManager.remove(aClass);
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

    public Class getCourse(int id) {
        Class aClass;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            aClass = entityManager.find(Class.class, id);
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
        return aClass;
    }

    @SuppressWarnings("unchecked")
    public Set<Class> getAllCourse() {
        Set<Class> classes = new HashSet<>();
//        List<Student> students;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            String hql = "from Class";
            Query query = entityManager.createQuery(hql);
            List<Class> temp = query.getResultList();
            classes.addAll(temp);
//            System.out.println(classes.get(0).getCourses());
            transaction.commit();
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
        return classes;
    }
}

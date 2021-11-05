package repositories;

import model.Course;
import model.Exam;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseDAO {

    public Course findCourseById(Long Id) {
        EntityManager em = EMF.getEMF().createEntityManager();
        return em.find(Course.class, Id);

    }

    public List<Course> getAllCourses() {
        EntityManager em = EMF.getEMF().createEntityManager();
        List<Course> courses = em.createQuery("Select c from Courses c").getResultList();
        return courses;


    }


    public void createCourse(Course course) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();

    }

    public void updateCourse(Course course) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();

    }


    public void deleteCourse(Course course) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        Course foundCourse = em.find(Course.class, course.getId());
        em.remove(course);
        em.getTransaction().commit();

    }

}
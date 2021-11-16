package repositories;

import model.Exam;
import model.Grade;
import model.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class GradeDAO {

    public Grade getGradeById(Long id) {
        EntityManager em = EMF.getEMF().createEntityManager();
        return em.find(Grade.class, id);
    }

    public List<Grade> getGradesByExam(Exam exam) {
        EntityManager em = EMF.getEMF().createEntityManager();
        return em.createQuery("FROM Grade WHERE exam_id LIKE '" + exam.getId() + "'", Grade.class).getResultList();
    }

    public List<Grade> getGradesByPerson(Person person) {
        EntityManager em = EMF.getEMF().createEntityManager();
        return em.createQuery("FROM Course WHERE person_id LIKE '" + person.getId() + "'" , Grade.class).getResultList();
    }

    public List<Grade> getAllGrades() {
        EntityManager em = EMF.getEMF().createEntityManager();
        return em.createQuery("FROM Grade", Grade.class).getResultList();
    }

    public void addGrade(Grade grade) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(grade);
        em.getTransaction().commit();
    }

    public void updateGrade(Grade grade)  {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(grade);
        em.getTransaction().commit();
    }

    public void deleteGrade(Grade grade) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.remove(grade);
        em.getTransaction().commit();
    }
}
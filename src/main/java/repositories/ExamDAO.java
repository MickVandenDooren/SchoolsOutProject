package repositories;

import model.Exam;

import javax.persistence.EntityManager;
import java.util.List;

public class ExamDAO {

    public Exam findExamById(Long Id){
        EntityManager em = EMF.getEMF().createEntityManager();
        return em.find(Exam.class,Id);

    }

    public List<Exam> getAllExams(){
        EntityManager em = EMF.getEMF().createEntityManager();
        List<Exam> exams = em.createQuery("Select e from Exams e").getResultList();
        return exams;


    }


    public void createExam (Exam exam){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(exam);
        em.getTransaction().commit();

    }

    public void updateExam(Exam exam){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(exam);
        em.getTransaction().commit();

    }

    public void deleteExam (Exam exam){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        Exam foundExam = em.find(Exam.class,exam.getId());
        em.remove(exam);
        em.getTransaction().commit();

    }

}
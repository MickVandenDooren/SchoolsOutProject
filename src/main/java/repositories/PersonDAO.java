package repositories;

import model.Person;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonDAO {

    public Person findPersonLogin(String login){
        EntityManager em = EMF.getEMF().createEntityManager();
        return em.find(Person.class,login);

    }

    public List<Person> getAllPersons(){
        EntityManager em = EMF.getEMF().createEntityManager();
        List<Person> ListofPersons = em.createQuery("Select p from Person p").getResultList();
        return ListofPersons;


    }


    public void createPerson (Person person){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();

    }

    public void updatePerson(Person person){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();

    }


    public void deletePerson (Person person){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        Person foundPerson = em.find(Person.class,person.getId());
        em.remove(person);
        em.getTransaction().commit();

    }



}
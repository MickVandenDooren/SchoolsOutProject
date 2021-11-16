package repositories;

import model.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO {


    public User findUserByLogin(String login){
        EntityManager em = EMF.getEMF().createEntityManager();
        return em.find(User.class,login);

    }

    public List<User> getAllUsers(){
        EntityManager em = EMF.getEMF().createEntityManager();
        List<User> users = em.createQuery("Select u from User u").getResultList();
        return users;
    }


    public static void createUser(User user){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void updateUser(User user){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }


    public void deleteUser (User user){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        User foundUser = em.find(User.class,user.getLogin());
        em.remove(foundUser);
        em.getTransaction().commit();
    }
}

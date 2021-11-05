package repositories;

import model.Person;

import javax.persistence.EntityManager;
import java.util.List;

public class ModuleDAO {

    public Module findModuleById(Long Id){
        EntityManager em = EMF.getEMF().createEntityManager();
        return em.find(Module.class,Id);

    }

    public List<Module> getAllModules(){
        EntityManager em = EMF.getEMF().createEntityManager();
        List<Module> modules = em.createQuery("Select m from Module m").getResultList();
        return modules;


    }

    public List<Module> getAllActiveModules() {
        EntityManager em = EMF.getEMF().createEntityManager();
        List<Module> modules = em.createQuery("Select m from Module m").getResultList();
        return modules;
    }

    public void createModule (model.Module module){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(module);
        em.getTransaction().commit();

    }

    public void updateModule(model.Module module){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(module);
        em.getTransaction().commit();

    }


    public void deleteModule (model.Module module){
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        Module foundModule = em.find(Module.class,module.getName());
        em.remove(module);
        em.getTransaction().commit();

    }


}
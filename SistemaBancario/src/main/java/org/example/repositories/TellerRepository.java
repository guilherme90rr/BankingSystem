package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Teller;
import java.util.List;
import java.util.Optional;


public class TellerRepository {
    private EntityManager entityManager;

    public TellerRepository(EntityManager entityManager) {this.entityManager = entityManager; }


    public void UpdateTeller(Teller teller)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(teller);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public List<Teller> GetAllTeller()
    {
        var jpql = "SELECT a FROM Teller a";
        var query = entityManager.createQuery(jpql, Teller.class);
        return query.getResultList();
    }

    public Optional<Integer> GetTellerById(int id)
    {
        var jpql = "SELECT a FROM Teller a WHERE id=:id";
        var query = entityManager.createQuery(jpql , Teller.class);
        query.setParameter("id", id);
        var Teller = query.getSingleResult();
        return Optional.ofNullable(id);
    }

    public void InsertTeller(Teller teller){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(teller);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteTellerById(int id){
        Teller teller = entityManager.find(Teller.class, id);
        entityManager.getTransaction().begin();
        try{
            if (teller != null){
                entityManager.remove(teller);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}

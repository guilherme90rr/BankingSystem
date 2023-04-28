package org.example.repositories;
import jakarta.persistence.EntityManager;
import org.example.models.Bank;

import java.util.List;
import java.util.Optional;

public class BankRepository {

    private EntityManager entityManager;

    public BankRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public void UpdateBank(Bank Bank)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(Bank);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public List<Bank> GetAllBank()
    {
        var jpql = "SELECT a FROM Banck a";
        var query = entityManager.createQuery(jpql, Bank.class);
        return query.getResultList();
    }

    public Optional<Integer> GetBankById(int id)
    {
        var jpql = "SELECT a FROM Bank a WHERE id=:id";
        var query = entityManager.createQuery(jpql , Bank.class);
        query.setParameter("id", id);
        var bank = query.getSingleResult();
        return Optional.ofNullable(id);
    }

    public void InsertBank(Bank Bank){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(Bank);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteBankById(int id){
        Bank bank = entityManager.find(Bank.class, id);
        entityManager.getTransaction().begin();
        try{
            if (bank != null){
                entityManager.remove(bank);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}

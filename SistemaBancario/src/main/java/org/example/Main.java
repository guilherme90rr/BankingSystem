package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.example.models.Bank;
import org.example.models.Customer;
import org.example.models.Loan;
import org.example.models.Teller;
import org.example.repositories.BankRepository;
import org.example.repositories.TellerRepository;
import org.example.repositories.TellerRepository;
import org.example.valueobjects.Location;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.
                    createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            var BankRepository = new BankRepository(entityManager);
            var LoanRepository = new TellerRepository(entityManager);

            Location Location = new Location("071101", "Teste teste", "8","São paulo");
            var bank = new Bank("Aviao teste");
            BankRepository.InsertBank(bank);
            BankRepository.GetAllBank();
            var bankao = new Bank("Moto Teste");
            BankRepository.UpdateBank(bankao);
            BankRepository.deleteBankById(1);



            entityManager.close();
            entityManagerFactory.close();
        } catch (Exception e){
            throw e;
        }
    }
}
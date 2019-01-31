package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.entities.Conta;

public class ContaDAO {

	private static ContaDAO instance;
    protected EntityManager entityManager;
     
    public static ContaDAO getInstance(){
              if (instance == null){
                       instance = new ContaDAO();
              }
               
              return instance;
    }

    private ContaDAO() {
              entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
              EntityManagerFactory factory = Persistence.createEntityManagerFactory("castProof");
              if (entityManager == null) {
                       entityManager = factory.createEntityManager();
              }

              return entityManager;
    }

    public Conta getById(final int id) {
              return entityManager.find(Conta.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Conta> findAll() {
              return entityManager.createQuery("FROM " + Conta.class.getName()).getResultList();
    }

    public void persist(Conta conta) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.persist(conta);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void merge(Conta conta) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.merge(conta);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }
}

package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.entities.Usuario;

public class UsuarioDAO {

	private static UsuarioDAO instance;
    protected EntityManager entityManager;
     
    public static UsuarioDAO getInstance(){
              if (instance == null){
                       instance = new UsuarioDAO();
              }
               
              return instance;
    }

    private UsuarioDAO() {
              entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
              EntityManagerFactory factory = Persistence.createEntityManagerFactory("castProof");
              if (entityManager == null) {
                       entityManager = factory.createEntityManager();
              }

              return entityManager;
    }

    public Usuario getById(final int id) {
              return entityManager.find(Usuario.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> findAll() {
              return entityManager.createQuery("FROM " + Usuario.class.getName()).getResultList();
    }

    public void persist(Usuario cliente) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.persist(cliente);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void merge(Usuario cliente) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.merge(cliente);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }
}

package iuh.fit.se.lab04_bai1_jsp_mvc.dao.daoImpl;

import iuh.fit.se.lab04_bai1_jsp_mvc.dao.UserDAO_JPA;
import iuh.fit.se.lab04_bai1_jsp_mvc.model.User_JPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class UserDAOImpl_JPAVersion implements UserDAO_JPA {
    private final EntityManager entityManager;

    public UserDAOImpl_JPAVersion(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insertUser(User_JPA user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User_JPA> getAllUsers() {
        TypedQuery<User_JPA> query = entityManager.createQuery("SELECT u FROM User_JPA u", User_JPA.class);
        return query.getResultList();
    }
}

package cz.cvut.usi.dao;

import cz.cvut.usi.model.Reservation;
import cz.cvut.usi.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Repository
@Transactional
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> list() {
        return em.createQuery("select u from User u").getResultList();
    }

    public boolean updateSimplePropertyByValue(Long id, String property, Object value, Class clazz) {
        String queryString = "UPDATE " + clazz.getSimpleName() + " AS e SET e." + property + "=:value WHERE e.id=" + id;
        int result = em.createQuery(queryString).setParameter("value", value).executeUpdate();
        return result == 0 ? false : true;
    }
}

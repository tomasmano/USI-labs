package cz.cvut.usi.dao;

import cz.cvut.usi.model.Reservation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReservationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Reservation task) {
        entityManager.persist(task);
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> list() {
        return entityManager.createQuery("select r from Reservation r").getResultList();
    }
}
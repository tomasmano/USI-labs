package cz.cvut.usi.service;

import cz.cvut.usi.dao.ReservationDAO;
import cz.cvut.usi.model.Reservation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tomy
 */
@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationDAO reservationDao;
    
    public void saveReservation(Reservation reservation){
        reservationDao.save(reservation);
    }
    
    public List<Reservation> list(){
        return reservationDao.list();
    }

    public ReservationDAO getReservationDao() {
        return reservationDao;
    }

    public void setReservationDao(ReservationDAO reservationDao) {
        this.reservationDao = reservationDao;
    }
    
}

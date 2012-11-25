package cz.cvut.usi.service;

import cz.cvut.usi.dao.ReservationDao;
import cz.cvut.usi.model.Reservation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomy
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationDao reservationDao;
    
    public void saveReservation(Reservation reservation){
        reservationDao.save(reservation);
    }
    
    public List<Reservation> list(){
        return reservationDao.list();
    }

    public ReservationDao getReservationDao() {
        return reservationDao;
    }

    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
    
}

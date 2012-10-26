package cz.cvut.usi.view;

import cz.cvut.usi.dao.ReservationDao;
import cz.cvut.usi.model.Reservation;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationHome {

    private static final Logger LOG = LoggerFactory.getLogger(ReservationHome.class);

    private Reservation reservation = new Reservation();
    private List<Reservation> reservations;

    @Autowired
    private ReservationDao reservationDao;


    public String getMessage() {
        LOG.debug("Returning message from task home bean");
        return "Hello from Spring";
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void saveReservation() {
        System.out.println(">>>>> save reservation called "+reservation);
        reservationDao.save(reservation);
        reservation = new Reservation();
        invalidateReservations();
    }
    
    public void saveReservationPrimefacesWay(ActionEvent actionEvent) {
        System.out.println(">>>>> save reservation called "+reservation);
        reservationDao.save(reservation);
        reservation = new Reservation();
        invalidateReservations();
    }

    private void invalidateReservations() {
        reservations = null;
    }

    public List<Reservation> getReservations() {
        if (reservations == null) {
            reservations = reservationDao.list();
        }
        return reservations;

    }
}

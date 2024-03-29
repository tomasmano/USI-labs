package cz.cvut.usi.view;

import cz.cvut.usi.model.Reservation;
import cz.cvut.usi.model.enums.ReservationActivity;
import cz.cvut.usi.model.enums.ReservationTool;
import cz.cvut.usi.service.ReservationService;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Component
public class ReservationHome {

    private static final Logger LOG = LoggerFactory.getLogger(ReservationHome.class);
    private Reservation reservation = new Reservation();
    private List<Reservation> reservations;
    private static final String[] reservationsActivities;
    private static final String[] reservationsTools;

    static {
        reservationsActivities = new String[]{
            ReservationActivity.hokej.toString(),
            ReservationActivity.futbal.toString(),
            ReservationActivity.fitness.toString(),
            ReservationActivity.sauna.toString()
        };
        reservationsTools = new String[]{
            ReservationTool.hokejka.toString(),
            ReservationTool.lopta.toString(),
            ReservationTool.tenisova_raketa.toString()
        };
    }
    
    @Autowired
    private ReservationService reservationService;

    public String getMessage() {
        LOG.debug("Returning message from task home bean");
        return "Hello from Spring";
    }

    public Reservation getReservation() {
        return reservation;
    }

    public String[] getReservationsActivities() {
        return reservationsActivities;
    }

    public String[] getReservationsTools() {
        return reservationsTools;
    }

    public String saveReservation() {
        reservationService.saveReservation(reservation);
        reservation = new Reservation();
        invalidateReservations();
        return null;
    }

    public void saveReservationPrimefacesWay(ActionEvent actionEvent) {
        reservationService.saveReservation(reservation);
        reservation = new Reservation();
        invalidateReservations();
    }

    private void invalidateReservations() {
        reservations = null;
    }

    public List<Reservation> getReservations() {
        if (reservations == null) {
            reservations = reservationService.list();
        }
        return reservations;

    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Rezervace zmeněna", ((Reservation) event.getObject()).getActivity().toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Rezervace zrušena", ((Reservation) event.getObject()).getActivity().toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

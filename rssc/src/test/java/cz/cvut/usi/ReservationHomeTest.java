package cz.cvut.usi;

import cz.cvut.usi.view.ReservationHome;
import cz.cvut.usi.model.Reservation;
import cz.cvut.usi.model.enums.ReservationActivity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationHomeTest extends AbstractServiceTest{

    @Autowired
    private ReservationHome reservationHome;

    @Test
    public void shouldGetCorrectMessage() {
        String message = reservationHome.getMessage();
        Assert.assertEquals("Hello from Spring", message);
    }

    @Test
    public void shouldSaveTaskAndResetTaskInstanceOnBean() {
        Reservation oldTask = reservationHome.getReservation();
        reservationHome.getReservation().setActivity(ReservationActivity.fitness.toString());
        reservationHome.saveReservation();
        Assert.assertNotNull("Saved reservation ID is null,probably not saved", oldTask.getId());
        Assert.assertNull("Reservation has not been reset", reservationHome.getReservation().getActivity());
        Assert.assertNull("Reservation has not been reset", reservationHome.getReservation().getId());
        Assert.assertNotSame("Task object has not been replaced", oldTask, reservationHome.getReservation());
    }
}

package cz.cvut.usi;

import cz.cvut.usi.view.ReservationHome;
import cz.cvut.usi.model.Reservation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TaskHomeTest {

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
        reservationHome.getReservation().setDescription("Sample Description");
        reservationHome.saveReservation();
        Assert.assertNotNull("Saved reservation ID is null,probably not saved", oldTask.getId());
        Assert.assertNull("Reservation has not been reset", reservationHome.getReservation().getDescription());
        Assert.assertNull("Reservation has not been reset", reservationHome.getReservation().getId());
        Assert.assertNotSame("Task object has not been replaced", oldTask, reservationHome.getReservation());
    }
}

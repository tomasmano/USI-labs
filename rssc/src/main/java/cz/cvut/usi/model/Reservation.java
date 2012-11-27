package cz.cvut.usi.model;

import cz.cvut.usi.model.enums.ReservationActivity;
import cz.cvut.usi.model.enums.ReservationType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 48)
//    @Enumerated(EnumType.STRING)
    private String activity;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", description=" + activity + ", time=" + time + '}';
    }
    
}

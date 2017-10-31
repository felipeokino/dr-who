package drwho.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


@Entity
@Table(name = "AppointmentSchedule")
public class AppointmentSchedule {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name="client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @Temporal(value = TemporalType.DATE)
    private Date dateSchedule;

    @Temporal(value = TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private Date startTimeScheduled;

    //Aqui também
    @Temporal(value = TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private Date endTimeScheduled;

    @NotNull
    private boolean isDeleted;

    public AppointmentSchedule() {
    }

    public AppointmentSchedule(Client client, Doctor doctor, Date dateSchedule, Date startTimeScheduled, Date endTimeScheduled, boolean isDeleted) {
        this.client = client;
        this.doctor = doctor;
        this.dateSchedule = dateSchedule;
        this.startTimeScheduled = startTimeScheduled;
        this.endTimeScheduled = endTimeScheduled;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(Date dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public Date getStartTimeScheduled() {
        return startTimeScheduled;
    }

    public void setStartTimeScheduled(Date startTimeScheduled) {
        this.startTimeScheduled = startTimeScheduled;
    }

    public Date getEndTimeScheduled() {
        return endTimeScheduled;
    }

    public void setEndTimeScheduled(Date endTimeScheduled) {
        this.endTimeScheduled = endTimeScheduled;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString(){
        return "Appointment Schedules {" +
                ", id=" + id +
                ", client" + client + '\'' +
                ", doctor" + doctor + '\'' +
                ", dateSchedule='" + dateSchedule + '\'' +
                ", startTimeScheduled" + startTimeScheduled + '\'' +
                ", endTimeScheduled='" + endTimeScheduled + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}

package drwho.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Doctors")
public class Doctor {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String cpf;

    @NotNull
    private String crm;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private String specialization;

    @OneToOne
    @JoinColumn(name="appointment_book_id")
    private AppointmentBook appointmentBook;

    @NotNull
    private boolean isDeleted;

    public Doctor(){ }

    public Doctor(String name, String cpf, String crm, String email, String phoneNumber, String address, String specialization, AppointmentBook appointmentBook, boolean isDeleted) {
        this.name = name;
        this.cpf = cpf;
        this.crm = crm;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.specialization = specialization;
        this.appointmentBook = appointmentBook;
        this.isDeleted = isDeleted;
    }

    //Getters and Setters


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getSpecialization() {
        return specialization;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public AppointmentBook getAppointmentBook() {
        return appointmentBook;
    }

    public void setAppointmentBook(AppointmentBook appointmentBook) {
        this.appointmentBook = appointmentBook;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String toString(){
        return "DoctorServices{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", crm='" + crm + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", specialization='" + specialization + '\'' +
                ", appointmentBook='" + appointmentBook + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}

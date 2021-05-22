package pl.lodz.p.it.ssbd2021.ssbd01.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "appointments")
@NamedQueries({
        @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
        @NamedQuery(name = "Appointment.findById", query = "SELECT a FROM Appointment a WHERE a.id = :id"),
        @NamedQuery(name = "Appointment.findByAppointmentDate", query = "SELECT a FROM Appointment a WHERE a.appointmentDate = :appointmentDate"),
        @NamedQuery(name = "Appointment.findByConfirmed", query = "SELECT a FROM Appointment a WHERE a.confirmed = :confirmed"),
        @NamedQuery(name = "Appointment.findByCanceled", query = "SELECT a FROM Appointment a WHERE a.canceled = :canceled"),
        @NamedQuery(name = "Appointment.findByRating", query = "SELECT a FROM Appointment a WHERE a.rating = :rating"),
        @NamedQuery(name = "Appointment.findByVersion", query = "SELECT a FROM Appointment a WHERE a.version = :version"),
        @NamedQuery(name = "Appointment.findByCreationDateTime", query = "SELECT a FROM Appointment a WHERE a.creationDateTime = :creationDateTime"),
        @NamedQuery(name = "Appointment.findByModificationDateTime", query = "SELECT a FROM Appointment a WHERE a.modificationDateTime = :modificationDateTime")})
public class Appointment extends AbstractEntity implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointments_generator")
    @SequenceGenerator(name = "appointments_generator", sequenceName = "appointments_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
    @NotNull
    private Long id;

    @Basic(optional = false)
    @Column(name = "appointment_date", nullable = false)
    @Future
    @NotNull
    private LocalDateTime appointmentDate;

    @Basic(optional = true)
    @Column(name = "confirmation_date_time", nullable = true)
    private LocalDateTime confirmationDateTime;

    @Basic(optional = false)
    @Column(name = "cancellation_date_time", nullable = false)
    @NotNull
    private LocalDateTime cancellation_date_time;

    @JoinColumn(name = "confirmed_by", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = true)
    private Account confirmedBy;

    @JoinColumn(name = "canceled_by", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = true)
    private Account canceledBy;

    public LocalDateTime getConfirmationDateTime() {
        return confirmationDateTime;
    }

    public void setConfirmationDateTime(LocalDateTime confirmationDateTime) {
        this.confirmationDateTime = confirmationDateTime;
    }

    public LocalDateTime getCancellation_date_time() {
        return cancellation_date_time;
    }

    public void setCancellation_date_time(LocalDateTime cancellation_date_time) {
        this.cancellation_date_time = cancellation_date_time;
    }

    public Account getConfirmedBy() {
        return confirmedBy;
    }

    public void setConfirmedBy(Account confirmedBy) {
        this.confirmedBy = confirmedBy;
    }

    public Account getCanceledBy() {
        return canceledBy;
    }

    public void setCanceledBy(Account canceledBy) {
        this.canceledBy = canceledBy;
    }

    @Basic(optional = false)
    @Column(name = "confirmed", nullable = false)
    @NotNull
    private Boolean confirmed = false;

    @Basic(optional = false)
    @Column(name = "canceled", nullable = false)
    @NotNull
    private Boolean canceled = false;

    @Column(name = "rating", columnDefinition = "numeric", precision = 2, scale = 1)
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    @Digits(integer = 1, fraction = 1)
    private Double rating;

    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Account doctor;

    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @ManyToOne
    private Account patient;

    /**
     * Tworzy nową instancję Appointment.
     */
    public Appointment() {
    }

    /**
     * Tworzy nowa instancje  Appointment.
     *
     * @param appointmentDate data wizyty
     * @param confirmed       status wizyty (potwierdzone)
     * @param canceled        status wizyty (odwolane)
     */
    public Appointment(LocalDateTime appointmentDate, Boolean confirmed, Boolean canceled) {
        this.appointmentDate = appointmentDate;
        this.confirmed = confirmed;
        this.canceled = canceled;
    }

    @Override
    public Long getId() {
        return id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


    public Account getDoctor() {
        return doctor;
    }

    public void setDoctor(Account doctor) {
        this.doctor = doctor;
    }

    public Account getPatient() {
        return patient;
    }

    public void setPatient(Account patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.ssbd2021.ssbd01.entities.Appointment[ id=" + id + " ]";
    }

}

package pl.lodz.p.it.ssbd2021.ssbd01.mow.dto.response;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import pl.lodz.p.it.ssbd2021.ssbd01.entities.Appointment;

public class AvailableAppointmentResponseDTO {

    @NotNull
    private Long id;
    
    @NotNull
    private Long doctorId;
    
    @NotNull
    private LocalDateTime date;
    
    @NotNull
    private Long version;

    /**
     * DTO do zwracania terminów wizyt.
     *
     * @param appointment termin wizyty na podstawie którego tworzone jest DTO.
     */
    public AvailableAppointmentResponseDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.doctorId = appointment.getDoctor().getId();
        this.date = appointment.getAppointmentDate();
        this.version = appointment.getVersion();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
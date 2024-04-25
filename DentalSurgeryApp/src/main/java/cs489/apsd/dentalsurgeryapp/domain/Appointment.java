package cs489.apsd.dentalsurgeryapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String status;

    @ManyToOne
    @JoinColumn(name = "surgeryId")
    @JsonIgnoreProperties({"appointments"})
    private Surgery surgery;

    @ManyToOne
    @JoinColumn(name = "dentistId")
    @JsonIgnoreProperties("appointments")
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonIgnoreProperties({"mailingAddress","appointments"})
    private Patient patient;

    public Appointment(LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }
}

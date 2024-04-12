package cs489.apsd.dentalsurgeryapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String patientNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDate dob;
    @OneToOne
    @JoinColumn(name = "mailingAddressId")
    private Address mailingAddress;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
}

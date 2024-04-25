package cs489.apsd.dentalsurgeryapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "dentists")
@NoArgsConstructor
@AllArgsConstructor
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String dentistID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialization;

    @OneToMany(mappedBy = "dentist")
    @JsonIgnoreProperties("dentist")
    private List<Appointment> appointments;

    public Dentist(String dentistID, String firstName, String lastName,
                   String phoneNumber, String email, String specialization) {
        this.dentistID = dentistID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
    }
}

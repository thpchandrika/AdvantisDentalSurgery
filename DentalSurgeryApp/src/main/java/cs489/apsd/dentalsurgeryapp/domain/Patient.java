package cs489.apsd.dentalsurgeryapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "mailingAddressId")
    @JsonIgnoreProperties("patient")
    private Address mailingAddress;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonIgnoreProperties("patient")
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();

    public Patient(String patientNumber, String firstName, String lastName, String phoneNumber, String email, LocalDate dob, Address mailingAddress) {
        this.patientNumber = patientNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dob = dob;
        this.mailingAddress = mailingAddress;
    }
}

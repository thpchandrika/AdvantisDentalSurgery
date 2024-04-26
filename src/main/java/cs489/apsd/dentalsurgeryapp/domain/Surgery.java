package cs489.apsd.dentalsurgeryapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "surgeries")
@NoArgsConstructor
@AllArgsConstructor
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String name;
    private String phone;
    @OneToOne(cascade = CascadeType.ALL, fetch =FetchType.EAGER)
    @JoinColumn(name = "surgeryLocationId", nullable = false)
    @JsonIgnoreProperties("patient")
    private Address locationAddress;

    @OneToMany(mappedBy = "surgery")
    @JsonIgnoreProperties("surgery")
    private List<Appointment> appointments;
}

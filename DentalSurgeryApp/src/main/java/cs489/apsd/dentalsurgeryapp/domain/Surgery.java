package cs489.apsd.dentalsurgeryapp.domain;

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
    @OneToOne
    @JoinColumn(name = "surgeryLocationId", nullable = false)
    private Address locationAddress;

    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments;
}

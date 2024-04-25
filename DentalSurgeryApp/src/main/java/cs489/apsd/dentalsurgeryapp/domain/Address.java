package cs489.apsd.dentalsurgeryapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String zip;
    private String city;
    private String state;

    @OneToOne(mappedBy = "mailingAddress")
    @JsonIgnoreProperties({"mailingAddress","appointments"})
    @JsonIgnore()
    private Patient patient;

    public Address(Integer id, String street, String zip, String city, String state) {
        this.id = id;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", street, zip, city, state);
    }
}

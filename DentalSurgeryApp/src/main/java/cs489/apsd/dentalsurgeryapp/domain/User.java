package cs489.apsd.dentalsurgeryapp.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles",
           joinColumns = @JoinColumn(name = "userId"),
           inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private List<Role> roles;
}

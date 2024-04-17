package cs489.apsd.dentalsurgeryapp.seeddata;

import cs489.apsd.dentalsurgeryapp.domain.Role;
import cs489.apsd.dentalsurgeryapp.constant.RoleType;
import cs489.apsd.dentalsurgeryapp.domain.User;
import cs489.apsd.dentalsurgeryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedUser implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============Seeding user data=======================");
        User officeManager = new User("officemanager", passwordEncoder.encode("manager"));
        Role managerRole = new Role(RoleType.OFFICEMANAGER.toString());
        officeManager.setRoles(List.of(managerRole));
        userService.addUser(officeManager);
    }
}

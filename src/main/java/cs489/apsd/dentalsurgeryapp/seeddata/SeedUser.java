package cs489.apsd.dentalsurgeryapp.seeddata;

import cs489.apsd.dentalsurgeryapp.domain.Role;
import cs489.apsd.dentalsurgeryapp.constant.RoleType;
import cs489.apsd.dentalsurgeryapp.domain.User;
import cs489.apsd.dentalsurgeryapp.service.RoleService;
import cs489.apsd.dentalsurgeryapp.service.UserService;
import cs489.apsd.dentalsurgeryapp.util.EmailService;
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
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============Seeding roles=======================");
        List<Role> roles = List.of(
                new Role(RoleType.OFFICE_MANAGER.toString()),
                new Role(RoleType.DENTIST.toString()),
                new Role(RoleType.PATIENT.toString())
        );
        roleService.addRoles(roles);

        System.out.println("==============Seeding user office manager with role=================");
        List<String> managerRoles = List.of(RoleType.OFFICE_MANAGER.toString());
        userService.createUserWithRole("officemanager", "manager",managerRoles);

       // emailService.sendTestEmail();
    }
}

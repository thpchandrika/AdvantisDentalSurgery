package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.domain.Role;
import cs489.apsd.dentalsurgeryapp.domain.User;
import cs489.apsd.dentalsurgeryapp.repository.UserRepository;
import cs489.apsd.dentalsurgeryapp.service.RoleService;
import cs489.apsd.dentalsurgeryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User createUserWithRole(String username, String password, List<String> roleNames) {
        List<Role> roles = new ArrayList<>();
        for (String roleName : roleNames) {
            Role role = roleService.findByName(roleName);
            if (role != null) {
                roles.add(role);
            }
        }
        User user = new User(username, passwordEncoder.encode(password));
        user.setRoles(roles);
        var newUser = userRepository.save(user);
        return  newUser;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

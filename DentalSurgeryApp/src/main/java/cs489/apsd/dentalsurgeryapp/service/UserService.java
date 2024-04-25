package cs489.apsd.dentalsurgeryapp.service;


import cs489.apsd.dentalsurgeryapp.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

     User addUser(User user);
     User createUserWithRole(String username, String password, List<String> role);
     User findByUsername(String username);
}

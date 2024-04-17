package cs489.apsd.dentalsurgeryapp.service;


import cs489.apsd.dentalsurgeryapp.domain.User;

import java.util.Optional;

public interface UserService {

    public User addUser(User user);
    public User findByUsername(String username);
}

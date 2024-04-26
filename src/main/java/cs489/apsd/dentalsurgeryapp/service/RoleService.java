package cs489.apsd.dentalsurgeryapp.service;

import cs489.apsd.dentalsurgeryapp.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> addRoles(List<Role> roles);
    Role findByName(String roleName);
}

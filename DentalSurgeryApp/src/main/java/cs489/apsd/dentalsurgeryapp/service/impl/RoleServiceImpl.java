package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.domain.Role;
import cs489.apsd.dentalsurgeryapp.repository.RoleRepository;
import cs489.apsd.dentalsurgeryapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> addRoles(List<Role> roles) {
        return roleRepository.saveAll(roles);
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}

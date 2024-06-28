package pl.coderslab.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Role;
import pl.coderslab.repository.InstitutionRepository;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void saveUserRole() {
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            Role role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
    }
}

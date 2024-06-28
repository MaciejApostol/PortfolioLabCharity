package pl.coderslab.service;

import pl.coderslab.entity.Category;
import pl.coderslab.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);
    void saveUserRole();
}

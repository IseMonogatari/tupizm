package org.preproject.CRUDSecAndFront.service;


import org.preproject.CRUDSecAndFront.dto.RoleDTO;
import org.preproject.CRUDSecAndFront.model.Role;
import org.preproject.CRUDSecAndFront.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolesRepository roleRepository;
    Role adminRole = new Role("ROLE_ADMIN");
    Role userRole = new Role("ROLE_USER");

    @Override
    public Role save(RoleDTO roleDTO) {
        return roleRepository.save(new Role("ROLE_" + roleDTO.getRole()));
    }

    @Override
    public Role userRole() {
        return userRole;
    }

    @Override
    public Role getRoleByName(String role) {
        return roleRepository.findByRole(role);
    }


}

package org.preproject.CRUDSecAndFront.service;


import org.preproject.CRUDSecAndFront.dto.RoleDTO;
import org.preproject.CRUDSecAndFront.model.Role;

public interface RoleService {
    Role save(RoleDTO roleDTO);
    Role userRole();

    Role getRoleByName(String role);
}

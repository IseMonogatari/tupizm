package org.preproject.CRUDSecAndFront.repository;


import org.preproject.CRUDSecAndFront.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}

package org.preproject.CRUDSecAndFront.service;



import org.preproject.CRUDSecAndFront.dto.UserRegistrationDTO;
import org.preproject.CRUDSecAndFront.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO userRegistrationDTO);
    User findUserById(Integer id);
    List<User> allUsers();
    User update(UserRegistrationDTO userRegistrationDTO);
    boolean deleteUser(Integer id);
}

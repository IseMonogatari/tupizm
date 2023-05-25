package org.preproject.CRUDSecAndFront.controller;

import org.preproject.CRUDSecAndFront.dto.UserRegistrationDTO;
import org.preproject.CRUDSecAndFront.model.User;
import org.preproject.CRUDSecAndFront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UnionRestController {

    @Autowired
    private UserService userService;

    // Получить список всех пользователей
    @GetMapping("get_allUsers")
    public List<User> getUserList(Model model) {
        return userService.allUsers();
    }

    // Получить авторизованного пользователя
    @GetMapping("get_authorizedUser")
    public User getAuthorizedUser(@AuthenticationPrincipal User user) {
        return user;
    }

    // Получить пользователя по id
    @GetMapping("get_user")
    public ResponseEntity<User> getUserById(@RequestParam("id") Integer id) {
        System.out.println("\n" + userService.findUserById(id).toString() + "\n");
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    // Получить пользователя по имени
    @GetMapping("get_user_by_name")
    public ResponseEntity<UserDetails> getUserByName(@RequestParam("name") String name) {
        System.out.println("\n" + userService.loadUserByUsername(name).toString() + "\n");
        return new ResponseEntity<>(userService.loadUserByUsername(name), HttpStatus.OK);
    }

    // Сохраняем пользователя
    @PostMapping("/add_user")
    public ResponseEntity<String> addUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        System.out.println("\n" + userRegistrationDTO.toString() + "\n");
        if (userService.save(userRegistrationDTO) != null) {
            return new ResponseEntity<>("Пользователь добавлен", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Данный пользователь не может быть админом",
                    HttpStatus.BAD_REQUEST);
        }
    }

    // Обновляем пользователя
    @PostMapping("/update_user")
    public ResponseEntity<String> updateUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        System.out.println("\n" + userRegistrationDTO.toString() + "\n" );

        if (userService.update(userRegistrationDTO) != null) {
            return new ResponseEntity<>("Пользователь обновлён", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Данный пользователь не может быть админом",
                    HttpStatus.BAD_REQUEST);
        }
    }

    // Удалить пользователяпо id
    @DeleteMapping("/delete_user")
    public void deleteUser(@RequestParam("id") Integer id) {
        userService.deleteUser(id);
    }
}

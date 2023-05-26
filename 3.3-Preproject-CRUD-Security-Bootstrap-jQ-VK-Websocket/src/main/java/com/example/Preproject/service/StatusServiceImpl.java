package com.example.Preproject.service;

import com.example.Preproject.model.Status;
import com.example.Preproject.model.User;
import com.example.Preproject.repository.StatusRepository;
import com.example.Preproject.repository.UsersRepository;
import com.example.Preproject.service.API.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private VkService vkService;

    @Override
    public void postCommentAdnChangeStatus(Integer userId, boolean status) {
        User user = usersRepository.findUserById(userId);
        vkService.sendCommentToVK("Я, " + user.getName() +
                ", хочу стать администратором Вашего сайта.");
        setStatusForUser(userId, status);
    }

    @Override
    public List<User> getUsersByStatus() {
        List<User> users = usersRepository.findAll().stream()
                .filter(u -> u.getStatus() != null && u.getStatus().isResponse())
                .collect(Collectors.toList());
        System.out.println("\nСписок всех людей со статусом true:");
        users.forEach(System.out::println);
        System.out.println("\n__________");
        return users;
    }

    @Override
    public void setStatusForUser(Integer userId, boolean status) {
        User user = usersRepository.findUserById(userId);
        user.setStatus(statusRepository.findStatusByResponse(status));
        usersRepository.save(user);
    }

    @Override
    public boolean getUserStatus(Integer userId) {
        Status status = usersRepository.findUserById(userId).getStatus();
        if (status != null) {
            return status.isResponse();
        }
        return false;
    }


}

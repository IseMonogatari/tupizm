package org.preproject.CRUDSecAndFront.service;

import org.preproject.CRUDSecAndFront.enums.UserStatus;
import org.preproject.CRUDSecAndFront.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VkService {
    //TODO Из ВК
    ResponseEntity<String> sendCommentToVK(String message);
    ResponseEntity<String> getCommentsFromVK();
    ResponseEntity<String> editCommentFromVK(String commentId, String message);
    ResponseEntity<String> deleteCommentFromVK(String commentId);

    //TODO из БД

    //TODO Работаетм со статусом пользователя через БД
    void changeUserStatus(Integer userId, String status);
    void changeUserStatus(Integer userId, UserStatus status);
    List<User> getUsersWhichWantToBeAAdmin(UserStatus status);
    Integer getUserStatusNumber(Integer userId);
}

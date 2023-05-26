package com.example.Preproject.service;

import com.example.Preproject.model.User;

import java.util.List;
public interface StatusService {
    List<User> getUsersByStatus();
    void setStatusForUser(Integer userId, boolean status);
    void postCommentAdnChangeStatus(Integer userId, boolean status);
    boolean getUserStatus(Integer userId);
}

package com.example.Preproject.repository;


import com.example.Preproject.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findStatusById(Integer id);
    Status findStatusByResponse(boolean response);
}

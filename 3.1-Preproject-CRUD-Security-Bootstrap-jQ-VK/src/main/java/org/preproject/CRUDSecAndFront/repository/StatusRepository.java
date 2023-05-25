package org.preproject.CRUDSecAndFront.repository;

import org.preproject.CRUDSecAndFront.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findStatusById(Integer id);
    Status findStatusByStatusNumber(Integer statusNumber);
}

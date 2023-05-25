package org.preproject.CRUDSecAndFront.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="status")
@NoArgsConstructor
public class Status {




    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "status_name")
    private String statusName;
    @Column(name = "status_number")
    private Integer statusNumber;
    @JsonIgnore
    @OneToMany(mappedBy = "status", cascade = {CascadeType.PERSIST, CascadeType.MERGE})//cascade = CascadeType.ALL REMOVE
    private Set<User> users;

    public Status(String statusName, Integer statusNumber) {
        this.statusName = statusName;
        this.statusNumber = statusNumber;
    }
}

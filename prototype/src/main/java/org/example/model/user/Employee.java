package org.example.model.user;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Employee extends UserApp {
    private String company;
    private String position;
}
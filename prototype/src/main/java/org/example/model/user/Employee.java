package org.example.model.user;

import lombok.Data;

@Data
public class Employee extends User {
    private String company;
    private String position;
}
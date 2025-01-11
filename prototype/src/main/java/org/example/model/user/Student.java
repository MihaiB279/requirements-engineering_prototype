package org.example.model.user;

import lombok.Data;
import org.example.model.internship.Application;

import java.util.List;

@Data
public class Student extends User {
    private String university;
    private String fieldOfStudy;
    private String cv;
    private List<Application> applications;
}
package org.example.model.internship;

import lombok.Data;

import java.util.List;

@Data
public class Internship {
    private String company;
    private String title;
    private String period;
    private List<String> skillsRequired;
    private String description;
    private String benefits;
    private String expectations;
    private List<Application> applications;
}
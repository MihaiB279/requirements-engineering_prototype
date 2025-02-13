package org.example.model.user;

import lombok.Data;
import org.example.model.internship.Internship;

import java.util.List;

@Data
public class Recruiter extends UserApp {
    private String company;
    private List<Internship> managedInternships;
}
package org.example.model.internship;

import lombok.Data;
import org.example.model.user.Student;

import java.util.Date;

@Data
public class Application {
    private Student student;
    private Internship internship;
    private ApplicationStatus status;
    private Date dateApplied;
}
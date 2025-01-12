package org.example.model.user;

import lombok.Data;
import org.example.model.internship.Application;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Student extends UserApp {
    private String university;
    private String fieldOfStudy;
    private String cv;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;
}
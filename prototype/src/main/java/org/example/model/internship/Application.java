package org.example.model.internship;

import lombok.Data;
import org.example.model.user.Student;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private Date dateApplied;
}

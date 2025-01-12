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

    // Many-to-One relationship with Student
    @ManyToOne
    @JoinColumn(name = "student_id")  // Optional: explicitly specify the foreign key column
    private Student student;

    // Many-to-One relationship with Internship
    @ManyToOne
    @JoinColumn(name = "internship_id")  // Optional: explicitly specify the foreign key column
    private Internship internship;

    private ApplicationStatus status;
    private Date dateApplied;
}

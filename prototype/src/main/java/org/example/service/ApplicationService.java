package org.example.service;

import org.example.model.internship.Application;
import org.example.model.internship.ApplicationStatus;
import org.example.model.internship.Internship;
import org.example.model.user.Student;
import org.example.repository.ApplicationRepository;
import org.example.repository.InternshipRepository;
import org.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final InternshipRepository internshipRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              StudentRepository studentRepository,
                              InternshipRepository internshipRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.internshipRepository = internshipRepository;
    }


    public void applyToInternship(Long studentId, Long internshipId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }
        Student student = studentOptional.get();

        // Retrieve the internship
        Optional<Internship> internshipOptional = internshipRepository.findById(internshipId);
        if (internshipOptional.isEmpty()) {
            throw new IllegalArgumentException("Internship not found with ID: " + internshipId);
        }
        Internship internship = internshipOptional.get();

        // Check if the student has already applied for this internship
        boolean alreadyApplied = internship.getApplications().stream()
                .anyMatch(application -> application.getStudent().getId().equals(studentId));

        if (alreadyApplied) {
            throw new IllegalArgumentException("Student has already applied for this internship.");
        }

        // Create a new application
        Application application = new Application();
        application.setStudent(student);
        application.setInternship(internship);
        application.setStatus(ApplicationStatus.PENDING);
        application.setDateApplied(new Date());

        applicationRepository.save(application);

        internship.getApplications().add(application);
        student.getApplications().add(application);
    }
}
